package de.uulm.in.vs.grn.p6b.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Listener implements Runnable {
    private BufferedReader input;
    private String date = "";

    public Listener(InputStream inputStream) {
        this.input = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void run() {
        while (true) {
            try {
                //read header of event/message and print out formatted text
                System.out.println(parse(input.readLine()));
                System.out.println("\n--------------------\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String parse(String header) throws IOException {

        //read date
        String date = input.readLine();
        date = date.replaceFirst("Date: ", "");

        //update current date
        if (!this.date.equals(date.split("\\s")[0])) {
            this.date = date.split("\\s")[0];
            System.out.println("----------\n" + this.date + "\n----------\n");
        }

        //parse message
        if (header.split("\\s")[1].equals("MESSAGE")) {

            //read username
            String username = input.readLine();

            //read message content
            StringBuilder text = new StringBuilder();
            String tmp = "";

            while (!(tmp = input.readLine()).equals("")) {
                text.append(tmp);
            }
            String message = text.toString();

            //format Strings
            username = username.replaceFirst("Username: ", "");
            message = message.replaceFirst("Text: ", "");

            //return formatted String
            return username + "\r\n" + message + "\r\n" + date.split("\\s")[1];
        }
        //parse event
        else {

            //read description
            StringBuilder text = new StringBuilder();
            String tmp = "";

            while (!(tmp = input.readLine()).equals("")) {
                text.append(tmp);
            }

            String description = text.toString();

            //format description
            description = description.replaceFirst("Description: ", "");

            //return formatted String
            return description + "\r\n" + date.split("\\s")[1];
        }
    }
}
