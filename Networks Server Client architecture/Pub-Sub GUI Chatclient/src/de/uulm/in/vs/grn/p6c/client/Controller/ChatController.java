package de.uulm.in.vs.grn.p6c.client.Controller;

import de.uulm.in.vs.grn.p6c.client.utils.PubSubMessages;
import de.uulm.in.vs.grn.p6c.client.utils.SendMessage;
import de.uulm.in.vs.grn.p6c.client.utils.Usernames;
import de.uulm.in.vs.grn.p6c.client.utils.UsernamesAutoUpdater;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatController {
    @FXML
    private ListView messageListView;
    @FXML
    private ListView usernamesListView;
    @FXML
    private TextField messageTextField;
    @FXML
    private Label invalidMessageLabel;

    private static Socket pubsub;
    private static Socket command;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public static void setPubsub(Socket socket) {
        pubsub = socket;
    }

    public static void setCommand(Socket socket) {
        command = socket;
    }

    @FXML
    public void initialize() {
        executorService.submit(new PubSubMessages(pubsub, messageListView));
        executorService.submit(new UsernamesAutoUpdater(command, usernamesListView));
    }

    public void updateUsernames() {
        executorService.submit(new Usernames(command, usernamesListView));
    }

    public void sendMessage() {
        executorService.submit(new SendMessage(command, messageTextField, invalidMessageLabel));
    }

    public void leave() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(command.getInputStream()));
            PrintStream output = new PrintStream(command.getOutputStream());

            //send bye request
            output.println("BYE GRNCP/0.1");
            output.println();
            output.flush();

            String responseHeader = input.readLine();

            //server successfully closed connection - shutdown threads, connection and close application
            if (responseHeader.endsWith("BYEBYE")) {
                input.readLine();
                executorService.shutdownNow();
                pubsub.close();
                command.close();
                System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
