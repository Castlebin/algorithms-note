package leetcode;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class ThreadPoolUtil {
    public static final ExecutorService exec = new ThreadPoolExecutor(5, 10,
            10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(50), new ThreadPoolExecutor.CallerRunsPolicy());
}
