package de.uulm.in.vs.grn.p6c.client;

import de.uulm.in.vs.grn.p6c.client.Controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //load initial window (login screen)
        Parent root = FXMLLoader.load(getClass().getResource("View/LoginView.fxml"));
        primaryStage.setTitle("GRNCP - Login");
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            //establish connection to command socket
            Socket command = new Socket("grn-services.lxd-vs.uni-ulm.de", 8122);

            LoginController.setCommand(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch(args);
    }
}
