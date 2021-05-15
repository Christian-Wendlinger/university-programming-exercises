import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class RequestHandler {
    private Socket client;
    private HashMap<String, String> DNSServiceMap;
    private PrintStream output;
    private Scanner input;
    private File dns;

    //initialize all necessary attributes
    public RequestHandler(Socket client, HashMap<String, String> DNSServiceMap, File dns) throws IOException {
        this.client = client;
        this.DNSServiceMap = DNSServiceMap;
        this.input = new Scanner(new InputStreamReader(client.getInputStream()));
        this.output = new PrintStream(client.getOutputStream());
        this.dns = dns;
    }

    public void execute() {
        try {
            //run until client sends "EXIT"
            boolean exit = false;
            while (!exit) {
                exit = parseInput(input.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //evaluate client input
    private boolean parseInput(String request) throws IOException {

        //server information
        String[] parts = request.split("\\s");
        System.out.println(request);

        //GET request
        if (parts[0].toLowerCase().equals("get")) {

            //invalid synthax
            if (parts.length != 2) {
                output.println("Invalid GET request!");
            }
            //valid synthax
            else {
                //key found - return key
                if (DNSServiceMap.get(parts[1]) != null) {
                    output.println(DNSServiceMap.get(parts[1]));
                }
                //key not found
                else {
                    output.println("Unkown Key!");
                }
            }
        }
        //PUT request
        else if (parts[0].toLowerCase().equals("put")) {

            //invalid synthax
            if (parts.length != 3) {
                output.println("Invalid PUT request!");
            }
            //valid synthax
            else {
                //key already exists - error
                if (DNSServiceMap.get(parts[1]) != null) {
                    output.println("Key already exists!");
                }
                //new key - add key to HashMap and append to file
                else {
                    DNSServiceMap.put(parts[1], parts[2]);
                    output.println("OK");
                    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(dns, true)));
                    writer.println(parts[1] + ":" + parts[2]);
                    writer.flush();
                }
            }
        }
        //EXIT request - clean exit - close all conenctions
        else if (parts[0].toLowerCase().equals("exit")) {
            output.println("BYE!");

            input.close();
            output.close();
            client.close();
            return true;
        }
        //empty request
        else if (parts[0].equals("")) {}
        //invalid request synthax
        else {
            output.println("Unknown Request!");
        }

        return false;
    }
}
