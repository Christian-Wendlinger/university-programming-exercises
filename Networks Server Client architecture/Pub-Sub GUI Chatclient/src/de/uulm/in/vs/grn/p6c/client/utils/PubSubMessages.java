package de.uulm.in.vs.grn.p6c.client.utils;

import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PubSubMessages extends Thread {
    private BufferedReader input;
    private ListView listView;
    private String date = "";

    public PubSubMessages(Socket pubsub, ListView listView) {
        try {
            this.input = new BufferedReader(new InputStreamReader(pubsub.getInputStream()));
            this.listView = listView;
            setDaemon(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                //read header of event/message and print out formatted text
                listView.getItems().add(parse(input.readLine()));
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
            listView.getItems().add(this.date);
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
