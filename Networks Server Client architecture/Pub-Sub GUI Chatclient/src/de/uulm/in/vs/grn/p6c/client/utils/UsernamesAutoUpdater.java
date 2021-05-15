package de.uulm.in.vs.grn.p6c.client.utils;

import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class UsernamesAutoUpdater extends Thread {
    private BufferedReader input;
    private PrintStream output;
    private ListView nameListView;

    public UsernamesAutoUpdater(Socket command, ListView nameListView) {
        try {
            this.input = new BufferedReader(new InputStreamReader(command.getInputStream()));
            this.output = new PrintStream(command.getOutputStream());
            this.nameListView = nameListView;
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            //send ping request
            output.println("PING GRNCP/0.1");
            output.println();
            output.flush();

            //parse response
            try {
                String responseHeader = input.readLine();

                //read response and update list with usernames
                if (responseHeader.endsWith("PONG")) {
                    String date = input.readLine();

                    StringBuilder usernamesBuilder = new StringBuilder();
                    String tmp = "";
                    while (!(tmp = input.readLine()).equals("")) {
                        usernamesBuilder.append(tmp);
                    }
                    String usernames = usernamesBuilder.toString();
                    usernames = usernames.replaceFirst("Usernames: ", "");
                    nameListView.getItems().clear();
                    for (String s : usernames.split(",")) {
                        nameListView.getItems().add(s);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //sleep 9 minutes - timeout after 10 minutes
            try {
                TimeUnit.MINUTES.sleep(9);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
