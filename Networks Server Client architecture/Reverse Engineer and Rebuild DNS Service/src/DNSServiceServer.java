import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class DNSServiceServer {
    public static void main(String[] args) {
        File dns = new File("info.txt");
        try {
            //read all existing associations and save in HashMap
            BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(dns)));
            HashMap<String, String> DNSServiceMap = new HashMap<>();
            String line = "";
            while ((line = input.readLine()) != null) {
                String[] parts = line.split(":");
                DNSServiceMap.put(parts[0], parts[1]);
            }

            //open Server
            try (ServerSocket server = new ServerSocket(7777)) {
                while (true) {
                    //start request handler
                    try (Socket client = server.accept()) {
                        System.out.println(client.getInetAddress() + " connected");
                        try {
                            RequestHandler handler = new RequestHandler(client, DNSServiceMap, dns);
                            handler.execute();
                        } catch (Exception e) {
                            System.out.println("Unexpected loss of connection");
                        }

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
