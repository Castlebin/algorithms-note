package algorithm;

public class N0000 {

    private static volatile int state = 1;

    private static volatile int value = 1;

    private static final int MAX = 20;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (value < MAX) {
                    while (state != 1) {
                        Thread.yield();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + value);
                    value++;
                    state = 2;
                }
            }
        }, "Thread-1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (value < MAX) {
                    while (state != 2) {
                        Thread.yield();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + value);
                    value++;
                    state = 1;
                }
            }
        }, "Thread-2").start();
    }

}
