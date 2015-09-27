package tunbridge;

import java.lang.InterruptedException;
import java.util.Random;

public class Farmer implements Runnable {
    private Bridge bridge;
    private Random random;

    public Farmer(Bridge b) {
        this.bridge = b;
        this.random = new Random();
    }

    public void crossBridge(Bridge bridge) throws InterruptedException {
        System.out.println("Waiting to enter bridge...");
        bridge.enter();
        System.out.println("Entering bridge...");
        Thread.sleep(1000 + random.nextInt(9000));
        bridge.leave();
        System.out.println("Left bridge!");
    }

    public void run() {
        try {
            this.crossBridge(this.bridge);
        } catch (InterruptedException e) {
            System.out.println("...Interrupted!");
        }
    }
}
