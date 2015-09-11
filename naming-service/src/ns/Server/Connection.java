package ns.Server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.lang.Runnable;

public class Connection implements Runnable {

    /**
     * The underlying socket connection.
     */
    private Socket sock;

    /**
     * Create a new connection handling thread.
     *
     * @param sock Socket connection to handle.
     */
    public Connection(Socket sock) {
        this.sock = sock;
    }

    /**
     * Write a string to the socket.
     *
     * @param data
     * @return void
     */
    protected void write(String data) throws IOException {
        OutputStream response = this.sock.getOutputStream();
        response.write(data.getBytes());
    }

    /**
     * Write a string to the socket, and close the connection.
     *
     * @param data
     * @return void
     */
    protected void end(String data) throws IOException {
        this.write(data);
        this.sock.close();
    }

    /**
     * Read all possible input, i.e. a single domain name.
     *
     * @return The domain name to resolve.
     */
    protected String readDomain() throws IOException {
        InputStream input = this.sock.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String domain = reader.readLine();
        this.sock.shutdownInput();
        return domain;
    }

    /**
     * Resolve a domain name to an IP address.
     *
     * @param domain
     * @return The IP address.
     */
    public String resolve(String domain) throws UnknownHostException {
        InetAddress hostAddress = InetAddress.getByName(domain);
        return hostAddress.getHostAddress();
    }

    /**
     * Handle a single domain name resolution request.
     */
    public void handle() throws IOException {
        String domain = this.readDomain();
        try {
            String ip = this.resolve(domain);
            this.end(ip + "\n");
        } catch (UnknownHostException e) {
            this.end("Unable to resolve host " + domain + "\n");
        }
    }

    /**
     * Run the thread: Handle the request.
     */
    public void run() {
        try {
            this.handle();
        } catch (IOException e) {
            System.out.println("Socket error: " + e.getMessage());
        }
    }

}
