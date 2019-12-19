package concurrency;

import org.junit.Test;

/**
 * 1117. Building H2O
 *
 * Runtime: 12 ms, faster than 69.91% of Java online submissions for Building H2O.
 * Memory Usage: 37.2 MB, less than 100.00% of Java online submissions for Building H2O.
 */
public class N1117 {

    class H2O {
        private int count;

        public synchronized void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            while (count % 3 == 0) {
                wait();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            count++;
            notifyAll();
        }

        public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {
            while (count % 3 != 0) {
                wait();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            count++;
            notifyAll();
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
                        h2O.hydrogen(() -> System.out.println('H'));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            } else if (c == 'O') {
                new Thread(() -> {
                    try {
                        h2O.oxygen(() -> System.out.println('O'));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
        Thread.sleep(1000);
    }

}
