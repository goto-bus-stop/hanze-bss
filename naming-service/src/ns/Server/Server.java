package ns.Server;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Server extends ServerSocket {
    public static final int DEFAULT_PORT = 6052;

    public Server() throws IOException {
        super();
    }
    public Server(int port) throws IOException {
        super(port);
    }

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        Server instance = null;
        try {
            instance = new Server(port);
        } catch (IOException e) {
            System.err.println("Could not create server: " + e.getMessage());
            return;
        }
        while (true) {
            Socket sock;
            try {
                sock = instance.accept();
            } catch (IOException e) {
                System.err.println("Could not accept connection: " + e.getMessage());
                return;
            }
            Runnable task = new Connection(sock);
            task.run();
        }
    }
}
