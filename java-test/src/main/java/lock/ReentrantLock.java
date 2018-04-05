package lock;

/**
 * 简单的可重入锁实现
 *
 * 关键在于 在上面的不可重入锁的基础上，
 * 增加了一个lockBy属性，表示当前被哪个线程获得了锁，
 * 另外就是一个进入锁区域代码块的次数，因为这是一个可重入锁了，必须要计数，并且只有当计数器重新归零时，才可以真正释放锁
 */
public class ReentrantLock {
    
    private boolean isLocked = false;
    private Thread lockBy = null;   // 当前被哪个线程获得
    private int lockedCount = 0;    // 计数器
    
    public synchronized void lock() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        while (isLocked && currentThread != lockBy) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockBy = currentThread;
    }
    
    public synchronized void unlock() {
        if (Thread.currentThread() == lockBy) {
            lockedCount--;
            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
    
}
