package ch05;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现一个独占锁
 */
public class Mutex implements Lock, Serializable {
    private static final long serialVersionUID = -2678189387960408303L;
    /**
     * Synchronizer providing all implementation mechanics
     */
    private final Sync sync = new Sync();

    /**
     * 静态内部类，自定义的队列同步器(独占锁，因此，覆盖AbstractQueuedSynchronizer中关于独占获取状态的protected方法即可，这几个protected方法的默认实现都是抛出异常，也就是无法直接使用！)
     */
    private static class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = -1756862128309476217L;

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 当state为0的时候可以获取锁（通过CAS操作保证只有一个线程能够获取成功）
        @Override
        protected boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }

            return false;
        }

        // 当state为1的时候可以释放锁（释放锁必须晚于得到锁，同时这是一个独占锁，所以无须CAS，只需判断一下）
        @Override
        protected boolean tryRelease(int acquires) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }

            setExclusiveOwnerThread(null);// 没有独占的线程了
            setState(0);// 重置state
            return true;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }
}
