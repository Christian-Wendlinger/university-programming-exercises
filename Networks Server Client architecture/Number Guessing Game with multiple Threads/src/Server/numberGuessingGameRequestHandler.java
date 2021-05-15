package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class numberGuessingGameRequestHandler implements Runnable {
    Socket client;

    public numberGuessingGameRequestHandler(Socket socket) {
        client = socket;
    }

    @Override
    public void run() {
        //communication
        try {
            InputStream input = client.getInputStream();
            OutputStream output = client.getOutputStream();

            //generate number, print info and send welcome message
            int number = ThreadLocalRandom.current().nextInt(51);
            System.out.println("Number for " + client.getInetAddress() + " = " + number);
            output.write("Welcome to the Number Guessing Game Server!\nYou get 6 turns to guess my secret number between 0 and 50!\nGood luck!-".getBytes());

            //gameloop
            for (int i = 0; i < 6; i++) {

                //read input from client
                String message = "";
                int b = 0;
                while ((b = input.read()) != 45) {
                    message += (char) b;
                }
                int clientGuess = Integer.parseInt(message);

                //compare input with number and send appropriate response
                if (clientGuess > number) {
                    if (i != 5) {
                        output.write(("\nMy number is smaller than your guess. Turns left: " + (5 - i) + "-").getBytes());
                    } else {
                        output.write("-".getBytes());
                    }
                } else if (clientGuess < number) {
                    if (i != 5) {
                        output.write(("\nMy number is greater than your guess. Turns left: " + (5 - i) + "-").getBytes());
                    } else {
                        output.write("-".getBytes());
                    }
                } else {
                    //game is won
                    output.write(("\nCongratulations you found the correct answer: " + number + ". Turns needed: " + (i + 1) + "-").getBytes());
                    System.out.println("Player with IP " + client.getInetAddress() + " has won!\n");
                    client.close();
                    return;
                }
            }
            //game is lost, print info
            output.write(("\n6 turns have passed and you couldn't guess my secret number " + number + "! Try again some other time!-").getBytes());
            System.out.println("Player with IP " + client.getInetAddress() + " has lost!\n");
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
