package concurrency;

/**
  两个线程交替打印 1 ~ 100 的简单实现

  lock.wait()
  lock.notify()

 */
public class N00001_1 {

    private static final int MAX = 100;

    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 1; i < MAX; i += 2) {
                        lock.notify();
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "Thread-1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 2; i <= MAX; i += 2) {
                        lock.notify();
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                        try {
                            if (i < MAX) {
                                lock.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "Thread-2").start();
    }

}
