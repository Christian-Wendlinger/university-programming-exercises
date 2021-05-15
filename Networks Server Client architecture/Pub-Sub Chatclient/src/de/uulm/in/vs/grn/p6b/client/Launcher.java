package de.uulm.in.vs.grn.p6b.client;

import java.io.IOException;
import java.net.Socket;

public class Launcher {
    public static void main(String[] args) {
        //establish connection and run listener
        try (Socket server = new Socket("grn-services.lxd-vs.uni-ulm.de", 8123)) {
            Listener receiver = new Listener(server.getInputStream());
            receiver.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

