package timedconnections;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.lang.Thread;
import java.lang.InterruptedException;
import java.util.concurrent.Semaphore;

public class TimedServer
{
    public static final int PORT = 2500;
    public static final int MAX_CONNECTIONS = 10;

    /**
     * Starts a socket server that accepts up to MAX_CONNECTIONS sockets.
     */
    public static void main(String[] args) {
        Semaphore connections = new Semaphore(MAX_CONNECTIONS);

        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Listening on " + PORT);

            while (true) {
                // Accept a new connection when we're currently handling <10.
                connections.acquire();
                Socket sock = server.accept();

                String available = connections.availablePermits() + " more available.";
                System.out.println("Accepted new connection: " + available);

                // Handle the new connection, decrementing the semaphore in the
                // disposer.
                Thread worker = new Thread(new Worker(sock, connections::release));
                worker.start();
            }
        }
        catch (InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
        }
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
