package timedconnections;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.lang.Thread;
import java.lang.InterruptedException;

public class TimedServer
{
    public static final int PORT = 2500;

    public static void main(String[] args) {
        Socket connection;

        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Listening on " + PORT);

            while (true) {
                connection = server.accept();

                Thread worker = new Thread(new Worker(connection));
                worker.start();
            }
        }
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
