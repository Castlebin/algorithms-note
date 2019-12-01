package com.heller.jcip.ch15;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public class SemaphoreOnLock {

    private final Lock lock = new ReentrantLock();

    // 条件谓词（permits > 0）
    private final Condition permitsAvailable = lock.newCondition();
    @GuardedBy("lock")
    private int permits;

    public SemaphoreOnLock(int permits) {
        lock.lock();
        try {
            this.permits = permits;
        } finally {
            lock.unlock();
        }
    }

    // 阻塞并直到permitsAvailable
    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (permits <= 0) {
                permitsAvailable.wait();
            }
            permits--;
        } finally {
            lock.unlock();
        }
    }

    public void release() throws InterruptedException {
        lock.lock();
        try {
            permits++;
            permitsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

}
