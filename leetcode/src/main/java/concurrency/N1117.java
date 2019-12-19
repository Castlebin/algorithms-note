package concurrency;

import leetcode.ThreadPoolUtil;
import org.junit.Test;
import sun.security.x509.IPAddressName;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 1117. Building H2O
 */
public class N1117 {

    class H2O {

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();

        }

        public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {

            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            
        }
    }

    @Test
    public void test() throws InterruptedException {
        H2O h2O = new H2O();
        String input = "HHOHHO";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == 'H') {
                new Thread(() -> {
                    try {
                        h2O.hydrogen(() -> System.out.println(c));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            } else if (c == 'O') {
                new Thread(() -> {
                    try {
                        h2O.oxygen(() -> System.out.println(c));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
        Thread.sleep(1000);
    }

}
