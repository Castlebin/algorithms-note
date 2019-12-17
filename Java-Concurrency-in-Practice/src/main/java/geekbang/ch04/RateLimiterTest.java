package geekbang.ch04;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class RateLimiterTest {
    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(10);
        ExecutorService exec = new ThreadPoolExecutor(5, 10,
                10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 1000; i++) {
            final int count = i;
            try {
                rateLimiter.acquire();
                exec.submit(() -> {
                    log.info(System.currentTimeMillis() + " -> " + count);
                });
            } catch (Exception e) {
                log.info(e.getMessage(), e);
            } finally {

            }
        }
    }
}
