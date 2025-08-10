import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ServerChat {
    // Synchronized set for thread safety
    static Set<ClientHandler> clients = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8080)) {
            System.out.println("Server listening on port 8080...");

            while (true) {
                Socket client = server.accept();
                System.out.println("New client: " + client.getRemoteSocketAddress());

                ClientHandler handler = new ClientHandler(client);
                clients.add(handler);
                new Thread(handler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
