package de.uulm.in.vs.grn.p5a;

import de.uulm.in.vs.grn.snake.SnakeBoardController;
import de.uulm.in.vs.grn.snake.SnakeDirection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class UpdateHandler implements Runnable {
    private DatagramSocket server;
    private SnakeBoardController snakeBoardController;

    public UpdateHandler(DatagramSocket server, SnakeBoardController snakeBoardController) {
        this.server = server;
        this.snakeBoardController = snakeBoardController;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //receive Data from server and use ByteBuffer for parsing
                byte[] data = new byte[this.server.getReceiveBufferSize()];
                DatagramPacket tmp = new DatagramPacket(data, this.server.getReceiveBufferSize());
                this.server.receive(tmp);
                ByteBuffer newData = ByteBuffer.wrap(tmp.getData());

                //first byte 0 == text message
                if (newData.get(0) == 0) {

                    //parse message and display it
                    StringBuilder str = new StringBuilder();
                    int len = newData.getInt(1);

                    for (int i = 0; i < len; i++) {
                        str.append((char) newData.get(5 + i));
                    }
                    snakeBoardController.renderText(str.toString());

                    //stop receiving data if Game Over was sent
                    if (str.toString().equals("Game Over!")) {
                        break;
                    }
                }
                //first byte 1 == game data
                else {
                    //parse own snake id and render it
                    byte[] snake_id = new byte[3];
                    System.arraycopy(newData.array(), 1, snake_id, 0, 3);
                    snakeBoardController.renderGame(snake_id);

                    //get number of events
                    int eventNum = newData.get(8);
                    int index = 9;

                    //loop through all events
                    for (int i = 0; i < eventNum; i++) {

                        //spawn new apple
                        if (newData.get(index) == 10) {
                            snakeBoardController.spawnApple(newData.get(index + 1), newData.get(index + 2));
                            index += 3;
                        }
                        //delete apple
                        else if (newData.get(index) == 11) {
                            snakeBoardController.despawnApple(newData.get(index + 1), newData.get(index + 2));
                            index += 3;
                        }
                        //snake data
                        else {
                            byte[] curSnakeId = new byte[3];
                            for (int j = 0; j < 3; j++) {
                                curSnakeId[j] = newData.get(index + 1 + j);
                            }
                            snakeBoardController.updateSnakeHead(curSnakeId, SnakeDirection.fromByteValue(newData.get(index + 4)), newData.get(index + 5), newData.get(index + 6), newData.get(index + 7));
                            index += 8;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //close connection after game is over
        this.server.close();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
