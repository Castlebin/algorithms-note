package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class Fibonacci {

    public long fibo(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibo(n - 1) + fibo(n - 2);
    }

    public long fibo2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        long f1 = 0;
        long f2 = 1;
        long fib = 1;
        for (int i = 2; i <= n; i++) {
            fib = f2 + f1;
            f1 = f2;
            f2 = fib;
        }

        return fib;
    }

    @Test
    public void test() {
        for (int n = 0; n < 20; n++) {
            Assert.assertEquals(fibo(n), fibo2(n));
        }
    }

}
