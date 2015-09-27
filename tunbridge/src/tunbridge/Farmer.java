package tunbridge;

import java.lang.InterruptedException;
import java.util.Random;

public class Farmer implements Runnable {
    private Bridge bridge;
    private Random random;
    protected String name;

    public Farmer(Bridge b) {
        this.bridge = b;
        this.random = new Random();
    }

    public void crossBridge(Bridge bridge) {
        System.out.println("[" + this.name + "] Waiting to enter bridge...");
        try {
            bridge.enter();
            System.out.println("[" + this.name + "] Entering bridge...");
            Thread.sleep(1000 + random.nextInt(9000));
            System.out.println("[" + this.name + "] Leaving bridge...");
        } catch (InterruptedException e) {
            System.out.println("...Interrupted!");
        } finally {
            bridge.leave();
        }
    }

    public void run() {
        this.crossBridge(this.bridge);
    }
}
