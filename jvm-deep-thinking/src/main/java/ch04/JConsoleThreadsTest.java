package ch04;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *  -Xmx10m
 */
public class JConsoleThreadsTest {
    /**
     * 线程死循环演示
     */
    public static void createBusyThread() {
        Thread thread = new Thread(() -> {
            while (true){} // 第41行
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待演示
     */
    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread");
        thread.start();
    }

    /**
     * 线程死锁等待演示
     */
    static class SynAddRunalbe implements Runnable {
        int a, b;
        public SynAddRunalbe(int a, int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public void run() {
            while (true) {
                synchronized (Integer.valueOf(a)) {
                    synchronized (Integer.valueOf(b)) {
                        System.out.println(a + b);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object obj = new Object();
        createLockThread(obj);
        br.readLine();
        Thread t1 = new Thread(new SynAddRunalbe(1, 2));
        Thread t2 = new Thread(new SynAddRunalbe(2, 1));
        t1.start();
        t2.start();
    }

}
