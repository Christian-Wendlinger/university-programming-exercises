package de.uulm.in.vs.grn.p6c.client.utils;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SendMessage extends Thread {
    private BufferedReader input;
    private PrintStream output;
    private TextField textField;
    private Label label;

    private static boolean expired = false;

    public SendMessage(Socket command, TextField textField, Label label) {
        try {
            input = new BufferedReader(new InputStreamReader(command.getInputStream()));
            output = new PrintStream(command.getOutputStream());
            this.textField = textField;
            this.label = label;
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDaemon(true);
    }

    @Override
    public void run() {
        //logged in
        if (!expired) {
            //preparse input
            if (!(textField.getText().matches(".*\r.*") || textField.getText().matches(".*\n.*") || textField.getText().getBytes().length > 512)) {

                //send message to server
                output.println("SEND GRNCP/0.1");
                output.println("Text: " + textField.getText());
                output.println();
                output.flush();

                //read response
                try {
                    String responseHeader = input.readLine();
                    String date = input.readLine();

                    //sent succesfully
                    if (responseHeader.endsWith("SENT")) {

                        //reset inputfields
                        textField.setText("");
                        label.setText(" ");
                        input.readLine();
                    }
                    //connection expired - prepare for relogin
                    else if (responseHeader.endsWith("EXPIRED")) {
                        input.readLine();
                        textField.setPromptText("Connection expired: Enter new Username");
                        textField.setText("");
                        expired = true;
                    }
                    //message couldn't be sent
                    else {
                        //show error reason
                        String reason = input.readLine();
                        reason = reason.replaceFirst("Reason: ", "");
                        label.setText(reason);
                        input.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //input is not valid
            else {
                label.setText("Invalid Input");
            }
        }
        //connection is expired
        else {
            //send login request
            String username = textField.getText();
            output.println("LOGIN GRNCP/0.1");
            output.println("Username: " + username);
            output.println();
            output.flush();

            //parse response
            try {
                String responseHeader = input.readLine();

                //succesfully logged in - show user
                if (responseHeader.endsWith("LOGGEDIN")) {
                    input.readLine();
                    input.readLine();
                    textField.setText("Logged in again");
                    textField.setPromptText("");
                    expired = false;
                }
                //login failed - show reason
                else {
                    String date = input.readLine();
                    textField.setText(input.readLine().replaceFirst("Reason: ", ""));
                    input.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
