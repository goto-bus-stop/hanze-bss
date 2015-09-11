package ns.Server;

import java.net.ServerSocket;
import java.io.IOException;

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
        try {
            Server instance = new Server(port);
        }
        catch (IOException e) {
            System.err.println("Could not create server: " + e.getMessage());
        }
    }
}