package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class numberGuessingGameClient {
    public static void main(String[] args) {
        try {
            //setup connection
            Socket socket = new Socket(InetAddress.getLocalHost(), 5555);
            Scanner scan = new Scanner(System.in);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            //read welcome message
            int b = 0;
            String message = "";
            while ((b = input.read()) != 45) {
                message += (char) b;
            }
            System.out.println(message);


            //setup game variables
            boolean gameOver = false;
            int numberGuess = -1;
            int counter = 0;

            //client game loop
            while (!gameOver) {

                //read and parse user input
                System.out.print("Guess: ");
                do {
                    try {
                        numberGuess = Integer.parseInt(scan.nextLine());

                        if (numberGuess < 0) throw new IllegalStateException();
                    } catch (Exception e) {
                        System.out.println("enter an Integer > 0");
                    }
                } while (numberGuess < 0);

                //send number to server
                output.write((Integer.toString(numberGuess) + "-").getBytes());

                //read and print response from server
                message = "";
                while ((b = input.read()) != 45) {
                    message += (char) b;
                }
                System.out.println(message);

                //game is won (winner message received) - break loop
                if (message.matches("^\nCongratulations.*")) {
                    gameOver = true;
                }
                // 6 Turns have passed - game lost
                else if (counter == 5) {

                    // read lose message and break loop
                    message = "";
                    while ((b = input.read()) != 45) {
                        message += (char) b;
                    }
                    System.out.println(message);
                    gameOver = true;
                }

                // turn counter
                counter++;
            }

            //close connection
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
