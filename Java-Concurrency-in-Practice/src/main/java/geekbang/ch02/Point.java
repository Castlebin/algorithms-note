package geekbang.ch02;

import java.util.concurrent.locks.StampedLock;

/**
 * 试一下StampedLock的用法
 *
 * 基本就是JDK StampedLock源码中的java doc中的示例代码
 *
 * StampedLock 支持的三种锁模式
 * 分别是：写锁、悲观读锁和乐观读，
 * 其中，写锁、悲观读锁的语义和 ReadWriteLock 的写锁、读锁的语义非常类似，
 * 允许多个线程同时获取悲观读锁，但是只允许一个线程获取写锁，
 * 写锁和悲观读锁是互斥的。
 * 不同的是：StampedLock 里的写锁和悲观读锁加锁成功之后，
 * 都会返回一个 stamp；
 * 然后解锁的时候，需要传入这个 stamp
 */
public class Point {

    private double x, y;

    private final StampedLock sl = new StampedLock();

    /**
     * 读锁的例子，排它锁
     */
    public void move(double deltaX, double deltaY) {
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    /**
     * 乐观读的使用例子
     * 失败时 获取 悲观读锁
     */
    public double distanceFromOrigin() {
        long stamp = sl.tryOptimisticRead();
        double currentX = x, currentY = y;
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    /**
     * 悲观读锁升级为写锁的过程
     */
    public void moveIfAtOrigin(double newX, double newY) {
        long stamp = sl.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                long ws = sl.tryConvertToWriteLock(stamp);
                if (ws != 0) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlock(stamp);
        }
    }

}
