package tunbridge;

import java.lang.InterruptedException;
import java.util.concurrent.Semaphore;

public class Bridge {
    private Semaphore lock;

    public Bridge() {
        /**
         * We can simply use a mutex semaphore to keep farmers from different
         * directions from entering. It isn't the most efficient, since
         * potentially multiple farmers from the same direction would be waiting
         * unnecessarily, but it works.
         */
        this.lock = new Semaphore(1);
    }

    public void enter() throws InterruptedException {
        this.lock.acquire();
    }

    public void leave() {
        this.lock.release();
    }
}
