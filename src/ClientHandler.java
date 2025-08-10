import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket client;
    private PrintWriter out;
    private String name;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))
        ) {
            out = new PrintWriter(client.getOutputStream(), true);

            // 1. Ask for name
            out.println("Enter your name: ");
            name = in.readLine();
            broadcast("📢 " + name + " has joined the chat.");

            // 2. Message reading and broadcasting loop
            String msg;
            while ((msg = in.readLine()) != null) {
                broadcast("[" + name + "]: " + msg);
            }

        } catch (IOException e) {
            System.err.println("Error with " + name + ": " + e.getMessage());
        } finally {
            ServerChat.clients.remove(this);
            broadcast("❌ " + name + " has left the chat.");
            try { client.close(); } catch (IOException ignored) {}
        }
    }

    // Send a message to all clients
    private void broadcast(String message) {
        synchronized (ServerChat.clients) {
            for (ClientHandler clientHandler : ServerChat.clients) {
                clientHandler.out.println(message);
            }
        }
    }
}
