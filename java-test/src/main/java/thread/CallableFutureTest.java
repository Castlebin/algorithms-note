package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                return 123;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);
        System.out.println("Is future done? " + future.isDone());
    //    Integer result = future.get();
        System.out.println("Is future done? " + future.isDone());
    //    System.out.println("result: " + result);
        executor.shutdownNow();
    }
}
