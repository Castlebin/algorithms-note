package concurrency;

import org.junit.Test;

/**
 * 1115. Print FooBar Alternately
 *
 * Details
 * Runtime: 16 ms, faster than 92.13% of Java online submissions for Print FooBar Alternately.
 * Memory Usage: 36.6 MB, less than 100.00% of Java online submissions for Print FooBar Alternately.
 */
public class N1115 {

    class FooBar {
        private int n;

        public FooBar(int n) {
            this.n = n;
        }

        private volatile int state = 1;

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while (state != 1) {
                    Thread.yield();
                }

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();

                state = 2;
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while (state != 2) {
                    Thread.yield();
                }

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();

                state = 1;
            }
        }
    }

    @Test
    public void test() {
        FooBar fooBar = new FooBar(10);
        new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.println("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.println("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
