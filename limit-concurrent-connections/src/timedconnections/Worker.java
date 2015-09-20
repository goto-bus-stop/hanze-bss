package timedconnections;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.InterruptedException;
import java.lang.Runnable;
import java.net.Socket;

class Worker implements Runnable
{
    private int sleepTime = 10;

    /**
     * Socket connection (oh really?)
     */
    private Socket connection;
    /**
     * Function to call after the socket connection is closed.
     */
    private Disposer disposer;

    /**
     * Create a socket handler.
     *
     * @param connection The socket connection to handle.
     * @param disposer Function to call when the socket connection closes.
     */
    public Worker(Socket connection, Disposer disposer) {
        this.connection = connection;
        this.disposer = disposer;
    }

    /**
     * Handle a connection, i.e. wait for 10 seconds and close.
     */
    public void run() {
        try {
            PrintWriter pout = new PrintWriter(connection.getOutputStream(), true);

            while (sleepTime > 0) {
                String s = (sleepTime == 1) ? " second." : " seconds.";
                pout.println("Sleeping " + sleepTime + " more " + s);
                Thread.sleep(1000);
                sleepTime -= 1;
            }

            // now close the socket connection
            connection.close();
        }
        catch (InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
        }
        catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
        finally {
            this.disposer.dispose();
        }
    }
}
