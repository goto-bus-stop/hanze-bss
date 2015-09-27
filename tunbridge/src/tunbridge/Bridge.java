package tunbridge;

import java.lang.InterruptedException;
import java.util.concurrent.Semaphore;

public class Bridge {
    private Semaphore lock;

    public Bridge() {
        this.lock = new Semaphore(1);
    }

    public void enter() throws InterruptedException {
        this.lock.acquire();
    }

    public void leave() throws InterruptedException {
        this.lock.release();
    }
}
