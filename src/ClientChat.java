import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {
    public static void main(String[] args) {
        String host = "localhost"; // or server IP
        int port = 8080;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to the server " + host + ":" + port);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            // Thread to read messages from the server
            new Thread(() -> {
                try {
                    String serverMsg;
                    while ((serverMsg = in.readLine()) != null) {
                        System.out.println(serverMsg);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed by the server.");
                }
            }).start();

            // Loop to send messages to the server
            while (true) {
                String userInput = scanner.nextLine();
                out.println(userInput);
                if (userInput.equalsIgnoreCase("/quit")) {
                    System.out.println("Disconnecting...");
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }
}
