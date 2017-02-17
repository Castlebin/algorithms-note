package ch02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class JavaAtomicTest {
    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> threads = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0; i<10000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }

        // 等待所有的线程执行完
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);
    }
}

class Counter {
    public AtomicInteger atomicI = new AtomicInteger(0);
    public int i = 0;

    // 使用CAS实现线程安全的计数器
    public void safeCount() {
        for (; ; ) {
            int i = atomicI.get();
            boolean success = atomicI.compareAndSet(i, ++i);
            if (success) {
                break;
            }
        }
    }

    // 非线程安全的计数器
    public void count() {
        i++;
    }
}
