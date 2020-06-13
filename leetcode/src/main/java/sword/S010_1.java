package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 10- I. 斐波那契数列
 */
public class S010_1 {

    /**
     * 题目要求 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * 汗😓
     */
    private static final int MOD = 1000000007;

    public int fib(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("参数不能小于0！");
        }
        if (n < 2) {
            return n;
        }
        int f0 = 0, f1 = 1;
        int fib = 0;
        for (int i = 2; i <= n; i++) {
            fib = (f0 + f1) % MOD;
            f0 = f1;
            f1 = fib;
        }
        return fib;
    }

    @Test
    public void test() {
        Assert.assertEquals(13, fib(7));
    }

}
