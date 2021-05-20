package concurrency;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 线程交替打印 这种要求 的通用解决方案
 */
public class N00001_2 {

    private static volatile int value = 1;

    private static final Semaphore odd = new Semaphore(1);
    private static final Semaphore even = new Semaphore(0);

    private static final int MAX = 20;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (value < MAX) {
                    try {
                        odd.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + (value++));
                    even.release();
                }
            }
        }, "Thread-1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (value < MAX) {
                    try {
                        even.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + (value++));
                    odd.release();
                }
            }
        }, "Thread-2").start();
    }

}
