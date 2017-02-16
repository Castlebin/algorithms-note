package ch05;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// 一个支持两个线程同时获取的锁
public class TwinsLock implements Lock, Serializable {
    private static final long serialVersionUID = 1919493472495628739L;

    private final Sync sync = new Sync(2);

    private static class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 6487507141725099983L;

        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large than zero.");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (;;) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount <0 || compareAndSetState(current, newCount)) {
                    System.out.println(newCount);// TODO 不懂啊!!
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (;;) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }

        @Override
        protected boolean tryAcquire(int acquires) {
            return compareAndSetState(getState(), getState() - 1);
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public Condition newCondition() {
        return sync.new ConditionObject();
    }
}
