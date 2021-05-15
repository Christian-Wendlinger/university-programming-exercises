
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

public class SockagramClient {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java SockagramClient filterType(0-6) Filepath PathForModifiedPicture (Host)");// usage in case of wrong input
            return;
        }
        String host = null;
        if (args.length == 4)// To make host default as there is only one test server provided but we wanted to keep it optional
            host = args[3];
        byte filterType = Byte.parseByte(args[0]);//scanning the different parameters
        File image = new File(args[1]);
        String path = args[2];
        sendImage(filterType, image, path, host);
    }

    public static void sendImage(byte filterType, File image, String path, String host) {
        try {
            if (host == null)
                host = "134.60.77.232";//if no server is provided via terminal, the default one is used

            Socket client = new Socket(host, 7777);//host is variable

            DataOutputStream dos = new DataOutputStream(client.getOutputStream());//initialize input and output streams

            DataInputStream dis = new DataInputStream(client.getInputStream());

            byte[] imageFile = Files.readAllBytes(image.toPath());

            dos.write(filterType);//first of all define the filter type

            dos.writeInt(imageFile.length);//then define the length of the payload

            dos.write(imageFile);//write the image into the stream

            dos.flush();


            File outputImage = new File(path);

            FileOutputStream outImage = new FileOutputStream(outputImage.getName());//create fileoutputstream for file creation

            byte status = dis.readByte();

            if (status == 0)
                System.out.println("Writing file...");// print the status just for getting a response
            else {
                System.out.println("Something went wrong!");
                return;
            }


            int payloadLength = dis.readInt();//read payload length

            byte[] payload = new byte[payloadLength];

            int b;
            while ((b = dis.read()) != -1) {
                outImage.write(b);//write into new file with applied filter
            }


            outImage.close();
            dis.close();
            dos.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
