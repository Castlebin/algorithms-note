package concurrency;

import org.junit.Test;

import java.util.function.IntConsumer;

/**
 * 1195. Fizz Buzz Multithreaded
 *
 * Runtime: 4 ms, faster than 99.93% of Java online submissions for Fizz Buzz Multithreaded.
 * Memory Usage: 36.3 MB, less than 100.00% of Java online submissions for Fizz Buzz Multithreaded.
 */
public class N1195 {

    class FizzBuzz {
        private int n;

        public FizzBuzz(int n) {
            this.n = n;
        }

        private int cur = 1;

        // printFizz.run() outputs "fizz".
        public synchronized void fizz(Runnable printFizz) throws InterruptedException {
            while (cur <= n) {
                if (cur % 3 == 0 && cur % 5 != 0) {
                    printFizz.run();
                    cur++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
            while (cur <= n) {
                if (cur % 5 == 0 && cur % 3 != 0) {
                    printBuzz.run();
                    cur++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            while (cur <= n) {
                if (cur % 15 == 0) {
                    printFizzBuzz.run();
                    cur++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public synchronized void number(IntConsumer printNumber) throws InterruptedException {
            while (cur <= n) {
                if (cur % 3 != 0 && cur % 5 != 0) {
                    printNumber.accept(cur);
                    cur++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    @Test
    public void test() throws InterruptedException {
        FizzBuzz fb = new FizzBuzz(15);
        new Thread(() -> {
            try {
                fb.fizz(() -> System.out.print("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fb.buzz(() -> System.out.print("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fb.fizzbuzz(() -> System.out.print("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fb.number((n) -> System.out.print(n));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(100);
    }

}
