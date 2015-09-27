package tunbridge;

import java.lang.InterruptedException;
import java.lang.Thread;
import java.util.Random;

public class Main {
    final private static int FARMERS = 10;

    public static void main(String[] args) {
        Bridge bridge = new Bridge();
        Random r = new Random();

        System.out.println("Running with " + FARMERS + " farmers...");
        // Enter a bunch of farmers from different directions.
        for (int i = 0; i < FARMERS; i++) {
            Farmer farmer;
            if (r.nextBoolean()) {
                farmer = new SouthBoundFarmer(bridge);
            } else {
                farmer = new NorthBoundFarmer(bridge);
            }
            cross(farmer);
        }
    }

    private static void cross(Farmer f) {
        new Thread(f).start();
    }
}
