package de.uulm.in.vs.grn.p5a;

import de.uulm.in.vs.grn.snake.SnakeBoardController;
import de.uulm.in.vs.grn.snake.SnakeDirection;
import de.uulm.in.vs.grn.snake.SnakeGame;
import de.uulm.in.vs.grn.snake.SnakeNetworkController;

import java.net.*;
import java.nio.ByteBuffer;

public class MultiplayerSnakeGame implements SnakeNetworkController {
    private final SnakeGame game;
    private final String host;
    private final int port;

    private DatagramSocket server;

    public MultiplayerSnakeGame(String host, int port) {
        this.host = host;
        this.port = port;

        //DatagramSocket to send and receive DatagramPackets
        try {
            this.server = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        this.game = new SnakeGame(this);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java " + MultiplayerSnakeGame.class.getName() + " HOSTNAME PORT");
            System.exit(1);
        }

        new MultiplayerSnakeGame(args[0], Integer.parseInt(args[1]));
    }

    @Override
    public void init(SnakeBoardController snakeBoardController) throws Exception {
        snakeBoardController.renderText("Connecting to Server...");

        //send empty byte to start new game
        this.server.send(new DatagramPacket(new byte[1], 1, InetAddress.getByName(this.host), this.port));

        //handle server requests in own Thread
        UpdateHandler updateHandler = new UpdateHandler(this.server, snakeBoardController);
        Thread t = new Thread(updateHandler);
        t.start();
    }

    @Override
    public void changeDirection(SnakeDirection direction) throws Exception {
        //send byte with new direction to server
        byte[] dir = {direction.toByteValue()};
        this.server.send(new DatagramPacket(dir, 1, InetAddress.getByName(this.host), this.port));
    }
}
