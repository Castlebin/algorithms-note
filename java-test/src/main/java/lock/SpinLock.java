package lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 简单的自旋锁实现
 *
 * 使用了CAS原子操作
 *
 * 自旋锁只是将当前线程不停地执行循环体，不进行线程状态的改变，所以响应速度更快。
 * 但当线程数不停增加时，性能下降明显，因为每个线程都需要执行，占用CPU时间。
 * 如果线程竞争不激烈，并且保持锁的时间短，则适合使用自旋锁
 */
public class SpinLock {
    
    private AtomicReference<Thread> sign = new AtomicReference<>();
    
    public void lock() {
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null, current)) {
        }
    }
    
    public void unlock() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }
    
}
