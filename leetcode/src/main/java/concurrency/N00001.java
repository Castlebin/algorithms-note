package concurrency;

/**
 两个线程交替打印 1 ~ 100 的简单实现

 while(state != CONDITION) {
    Thread.yield();
 }

 */
public class N00001 {

    private static volatile int state = 1;

    private static volatile int value = 1;

    private static final int MAX = 100;

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
