package ns.Client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    /**
     * Regex for checking if a thing looks kinda like an IP address.
     */
    private static final String IP_REGEX =
        "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";

    /**
     * Naming service server to connect to.
     */
    private String host;

    /**
     * Port number to connect to.
     */
    private int port;

    /**
     * Connection to the name server.
     */
    private Socket sock;

    /**
     * Create a NS client instance.
     *
     * @param host Hostname to connect to.
     * @param port Port number to connect to.
     */
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Connect to the name server.
     */
    protected void connect() throws IOException, UnknownHostException {
        this.sock = new Socket(this.host, this.port);
    }

    /**
     * Read a single line from a bare InputStream.
     *
     * @return The line.s
     */
    protected String readLine(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
            input));
        return reader.readLine();
    }

    /**
     * Retrieve the IP address for a given domain from the NS server.
     *
     * @param domain
     * @return Raw server response.
     */
    public String resolve(String domain)
            throws IOException, UnknownHostException {
        this.connect();

        OutputStream request = this.sock.getOutputStream();
        request.write((domain + "\n").getBytes());

        return this.readLine(this.sock.getInputStream());
    }

    /**
     * Exit the client.
     */
    public void exit() {
        if (this.sock != null && !this.sock.isClosed()) {
            try {
                this.sock.close();
            } catch (Exception e) {
                System.err.println("Socket Error: " + e.getMessage());
            }
        }
    }

    /**
     * Run the client.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println(
                "No domain name given. Usage: ./client [domain name]");
            return;
        }

        String domain = args[0];
        Client client = new Client("localhost", 6052);

        try {
            String ip = client.resolve(domain);
            // send output to stderr if resolution failed
            if (ip.matches(IP_REGEX)) {
                System.out.println(ip);
            } else {
                System.err.println(ip);
            }
        } catch (UnknownHostException e) {
            System.err.println(
                "Could not resolve name server hostname: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Socket Error: " + e.getMessage());
        } finally {
            client.exit();
        }
    }
}
