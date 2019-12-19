package concurrency;

import leetcode.ThreadPoolUtil;
import org.junit.Test;

import java.util.function.IntConsumer;

/**
 * 1116. Print Zero Even Odd
 *
 * Details
 * Runtime: 4 ms, faster than 99.76% of Java online submissions for Print Zero Even Odd.
 * Memory Usage: 35.2 MB, less than 100.00% of Java online submissions for Print Zero Even Odd.
 */
public class N1116_1 {

    class ZeroEvenOdd {
        private int n;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        private boolean printZero = true, printOdd = false, printEven = false;

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            synchronized (this) {
                for (int i = 1; i <= n; i++) {
                    while (!printZero) {
                        wait();
                    }
                    printNumber.accept(0);
                    if (i % 2 == 1) {
                        printOdd = true;
                    } else {
                        printEven = true;
                    }
                    printZero = false;
                    notifyAll();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            synchronized (this) {
                for (int i = 2; i <= n; i += 2) {
                    while (!printEven) {
                        wait();
                    }
                    printNumber.accept(i);
                    printZero = true;
                    printEven = false;
                    notifyAll();
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            synchronized (this) {
                for (int i = 1; i <= n; i += 2) {
                    while (!printOdd) {
                        wait();
                    }
                    printNumber.accept(i);
                    printZero = true;
                    printOdd = false;
                    notifyAll();
                }
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
