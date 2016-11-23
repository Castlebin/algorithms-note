package thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CallableExecutorTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 2),
                callable("task2", 1),
                callable("task3", 3));

        String result = executor.invokeAny(callables);
        System.out.println(result);
    }

    public static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " start...");
            TimeUnit.SECONDS.sleep(sleepSeconds);
            System.out.println(Thread.currentThread().getName() + " is done.");
            return result;
        };
    }
}
