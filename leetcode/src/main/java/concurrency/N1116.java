package concurrency;

import leetcode.ThreadPoolUtil;
import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * 1116. Print Zero Even Odd
 *
 * Details
 * Runtime: 5 ms, faster than 46.18% of Java online submissions for Print Zero Even Odd.
 * Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for Print Zero Even Odd.
 */
public class N1116 {

    class ZeroEvenOdd {
        private int n;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        private Semaphore printZero = new Semaphore(1);
        private Semaphore printOdd = new Semaphore(0);
        private Semaphore printEven = new Semaphore(0);

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                printZero.acquire();
                printNumber.accept(0);
                if (i % 2 == 1) {
                    printOdd.release();
                } else {
                    printEven.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                printEven.acquire();
                printNumber.accept(i);
                printZero.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                printOdd.acquire();
                printNumber.accept(i);
                printZero.release();
            }
        }
    }

    @Test
    public void test() {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        IntConsumer consumer = System.out::print;
        ThreadPoolUtil.exec.submit(() -> {
            try {
                zeroEvenOdd.zero(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        ThreadPoolUtil.exec.submit(() -> {
            try {
                zeroEvenOdd.even(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        ThreadPoolUtil.exec.submit(() -> {
            try {
                zeroEvenOdd.odd(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
