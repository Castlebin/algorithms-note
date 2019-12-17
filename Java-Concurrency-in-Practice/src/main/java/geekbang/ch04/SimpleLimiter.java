package geekbang.ch04;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 按照Guava RateLimiter的原理，实现一个简单的限流器
 * <p>
 * 简化模型，该限流器 令牌桶大小为 1，限制流速为 1请求 每秒
 * <p>
 * 关键方法在于，reserve()中计算下一次发放令牌的时间
 */
@Slf4j
public class SimpleLimiter {

    /**
     * 下一次发放令牌的时间
     */
    private long next = System.nanoTime();

    /**
     * 发放令牌的时间间隔，单位纳秒 (1s)
     */
    private long interval = 1_000_000_000;

    // 预占令牌，返回能够获取令牌的时间
    private synchronized long reserve(long now) {
        // 将请求令牌的时间和现在的下一次发放令牌的时间做比较
        // 如果请求时间在下一次发放令牌的时间之后，重新计算下一次发放令牌的时间
        if (now > next) {
            // 说明现在就可以获取令牌，所以将下一次发放令牌的时间重置为现在
            next = now;
        }
        // 能够获取令牌的时间（能够开始执行任务的时间）
        long at = next;
        // 设置下一次发放令牌的时间
        next += interval;
        // 返回线程需要等待到可以执行的时间
        return Math.max(at, 0L);
    }

    /**
     * 申请令牌
     */
    public void acquire() {
        // 申请令牌的时间
        long now = System.nanoTime();
        // 预占令牌
        long at = reserve(now);
        long waitTime = Math.max(at - now, 0);
        // 按条件等待
        if (waitTime > 0) {
            try {
                TimeUnit.NANOSECONDS.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SimpleLimiter limiter = new SimpleLimiter();
        ExecutorService exec = new ThreadPoolExecutor(5, 10,
                10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 1000; i++) {
            final int count = i;
            try {
                limiter.acquire();
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
