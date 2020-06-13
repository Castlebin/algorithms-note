package sword;

/**
 10- II. 青蛙跳台阶问题
 */
public class S010_2 {

    /**
     * 题目要求 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * 汗😓
     */
    private static final int MOD = 1000000007;

    public int numWays(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("参数不能小于0！");
        }
        if (n < 2) {
            return 1;   // 0， 1 都返回1，注意了，初始值，与 fibnacci数列有点不一样
        }
        int f0 = 1, f1 = 1;// 0， 1 都返回1，注意了，初始值，与 fibnacci数列有点不一样
        int fib = 0;
        for (int i = 2; i <= n; i++) {
            fib = (f0 + f1) % MOD;
            f0 = f1;
            f1 = fib;
        }
        return fib;
    }

}
