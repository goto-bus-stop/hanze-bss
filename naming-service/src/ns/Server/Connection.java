package ns.Server;

import java.net.Socket;
import java.lang.Runnable;

public class Connection implements Runnable {

    private Socket sock;

    public Connection(Socket sock) {
        this.sock = sock;
    }

    public void run() {
        //
        System.out.println("Socket connected");
    }

}
