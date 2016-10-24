package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadTimeOutTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            Thread.sleep(10 * 1000);
            return 5;
        };

        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread thread = new Thread(task);
        thread.start();

        // 简单的实现线程超时的任务
        try {
            Integer result = task.get(2, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            System.out.println("task timeout");
            task.cancel(true);
        }
        if (!task.isCancelled()) {
            System.out.println(task.get());
        }

        try {
            System.out.println(new ThreadWithTimeOut<>(callable, 5000).runTask());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("task timeout");
        }

        try {
            System.out.println(new ThreadWithTimeOut<>(callable, 20 * 1000).runTask());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("task timeout");
        }
    }
}

