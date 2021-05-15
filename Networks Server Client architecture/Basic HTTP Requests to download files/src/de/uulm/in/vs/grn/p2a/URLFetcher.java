package de.uulm.in.vs.grn.p2a;
import java.io.*;
import java.net.Socket;
import java.net.URL;


public class URLFetcher {
    public static void main(String[] args) throws Exception {
        //test txts: http://www.httrack.com/license.txt

        /*test pictures:
            http://s611413986.website-start.de/s/cc_images/cache_48240802.jpg, http://www.html-testseite.de/seitenbilder/waermeschutz.jpg,
            http://27zoll-monitor-testseite.de/wp-content/uploads/2016/09/cropped-61dWyBs9QzL._SL1000_-Kopie-2-1.jpg,
            http://bilder.hifi-forum.de/medium/888397/stereoplay-test-seite-1_781763.png
         */

        //test pdfs: http://www.orimi.com/pdf-test.pdf, http://www.informatik.uni-leipzig.de/~ebling/PuP/latex_bsp.pdf

        //test htmls: http://www.theplantlist.org/1.1/statistics/, http://www.httrack.com/html/overview.html


        //allows to download multiple files sequentially
        for (String arg : args) {
            getFileFromURL(new URL(arg));
        }

    }

    private static void getFileFromURL(URL link) throws Exception {
        //open connection and get Bytestreams
        Socket client = new Socket(link.getHost(), 80);
        InputStream input = client.getInputStream();
        OutputStream output = client.getOutputStream();

        //send minimal request
        output.write(("GET " + link.getPath() + " HTTP/1.1\r\nHost: " + link.getHost() + "\r\n\r\n").getBytes());
        output.flush();

        //read response Header
        String responseHeader = "";
        while (!responseHeader.endsWith("\r\n\r\n")) {
            responseHeader += (char) input.read();
        }

        //check Response Code
        if (Integer.parseInt(responseHeader.split("\n")[0].split(" ")[1]) == 200) {
            //create File out of Response Body
            File outputFile = new File(link.getPath().matches(".*\\.\\w{3,4}") ? link.getPath() : link.getHost().replaceFirst("www.", "").replaceFirst("\\..*", "") + ".html");
            FileOutputStream out = new FileOutputStream(outputFile.getName());

            //write raw data to file
            int b;
            while ((b = input.read()) != -1) {
                out.write(b);
            }

            out.close();
        } else {
            throw new Exception("HTTP response code:" + responseHeader.split("\n")[0].split(" ")[1]);
        }
        //close streams and connection
        input.close();
        output.close();
        client.close();
    }
}
