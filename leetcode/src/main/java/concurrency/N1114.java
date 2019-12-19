package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1114. Print in Order
 *
 * Details
 * Runtime: 8 ms, faster than 98.10% of Java online submissions for Print in Order.
 * Memory Usage: 35.7 MB, less than 100.00% of Java online submissions for Print in Order.
 */
public class N1114 {

    private AtomicInteger state = new AtomicInteger(1);

    public void first(Runnable printFirst) throws InterruptedException {
        while (!state.compareAndSet(1, 2)) {
            Thread.yield();
        }
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (!state.compareAndSet(2, 3)) {
            Thread.yield();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (!state.compareAndSet(3, 4)) {
            Thread.yield();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

}
