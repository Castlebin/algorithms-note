package ch04;

public class InterruptThreadTest2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new TestThread());
        t1.start();
        t1.interrupt();
    }

    static class TestThread implements Runnable {
        @Override
        public void run() {
            SleepUtils.second(10);
            System.out.println("TestThread wake up.");
        }
    }
}
