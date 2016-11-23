package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunableTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            while (true) {
                Thread.sleep(1);// 去掉这一行，线程就无法终止
                System.out.println("hello, " + Thread.currentThread().getName());
            }
        });
        System.out.println("hello, " + Thread.currentThread().getName());
        executorService.shutdownNow();// 使用shutdown()，线程也无法终止
        // shutdownNow()和shutdown()可以多次调用
    }
}
