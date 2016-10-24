package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadWithTimeOut<T> {
    private Callable<T> callable;
    private int timeout;

    public ThreadWithTimeOut(Callable<T> callable, int timeout) {
        this.callable = callable;
        this.timeout = timeout;
    }

    public T runTask() throws InterruptedException, ExecutionException, TimeoutException {
        FutureTask<T> task = new FutureTask<>(callable);
        Thread thread = new Thread(task);
        thread.start();
        try {
            return task.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            task.cancel(true);
            throw e;
        }
    }

}
