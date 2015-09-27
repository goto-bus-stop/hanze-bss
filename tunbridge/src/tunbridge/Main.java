package tunbridge;

import java.lang.InterruptedException;
import java.lang.Thread;

public class Main {
    public static void main(String[] args) {
        Bridge bridge = new Bridge();

        cross(new SouthBoundFarmer(bridge));
        cross(new NorthBoundFarmer(bridge));
    }

    private static void cross(Farmer f) {
        new Thread(f).start();
    }
}
