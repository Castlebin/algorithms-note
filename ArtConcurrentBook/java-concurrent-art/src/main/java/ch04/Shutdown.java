package ch04;

import java.util.concurrent.TimeUnit;

public class Shutdown {
    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread thread_1 = new Thread(one, "CountThread-1");
        Runner two = new Runner();
        thread_1.start();
        new Thread(two, "CountThread-2").start();
        TimeUnit.MILLISECONDS.sleep(1);
        thread_1.interrupt();
        two.cancel();
    }

    private static class Runner implements Runnable {
        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println(Thread.currentThread().getName() + " - Count i = " + i);
        }

        public void cancel() {
            on = false;
        }
    }
}
