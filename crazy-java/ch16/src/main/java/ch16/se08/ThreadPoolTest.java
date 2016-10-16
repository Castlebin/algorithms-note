package ch16.se08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(6);
        Runnable target = () -> {
            for (int i = 0; i<20; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        };

        pool.submit(target);
        pool.submit(target);

        // 关闭线程池
        pool.shutdown();
    }
}
