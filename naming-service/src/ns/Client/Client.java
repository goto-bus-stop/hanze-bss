package ns.Client;

public class Client {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("No domain name given. Usage: ./client [domain name]");
            return;
        }
        String domain = args[0];
        System.out.println("Looking up " + domain + "…");
    }
}