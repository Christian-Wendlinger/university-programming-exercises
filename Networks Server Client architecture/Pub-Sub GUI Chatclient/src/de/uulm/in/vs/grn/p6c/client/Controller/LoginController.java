package de.uulm.in.vs.grn.p6c.client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class LoginController {
    @FXML
    private Label label;
    @FXML
    private TextField textField;
    @FXML
    private Button button;

    private static Socket pubsub;
    private static Socket command;

    public void login() {
        String username = textField.getText();
        try {
            PrintStream output = new PrintStream(command.getOutputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(command.getInputStream()));

            //send login request if username is valid
            if (username.matches("[a-zA-Z0-9]{3,15}")) {
                output.println("LOGIN GRNCP/0.1");
                output.println("Username: " + username);
                output.println();
                output.flush();

                //parse response
                String responseHeader = input.readLine();
                if (responseHeader.endsWith("LOGGEDIN")) {
                    //switch scenes after successful login
                    pubsub = new Socket("grn-services.lxd-vs.uni-ulm.de", 8123);
                    ChatController.setPubsub(pubsub);
                    ChatController.setCommand(command);

                    Stage primaryStage = (Stage) button.getScene().getWindow();
                    primaryStage.setTitle("GRNCP - Chat");
                    primaryStage.setScene(new Scene(new FXMLLoader(getClass().getResource("../View/ChatView.fxml")).load(), 600, 400));

                }
                //sgow reason why loginf ailed
                else {
                    String date = input.readLine();
                    String reason = input.readLine();
                    input.readLine();

                    label.setText(reason.replaceFirst("Reason: ", ""));
                }
            }
            //show that username is invalid
            else {
                label.setText("Invalid username");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setCommand(Socket socket) {
        command = socket;
    }
}
