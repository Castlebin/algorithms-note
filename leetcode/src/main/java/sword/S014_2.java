package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 面试题14- I. 剪绳子
 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 若干段，总段数记为 m（m、n都是整数，n>1并且m>1），
 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。     （说明数字可能会很大）

 提示：
    2 <= n <= 1000                  （数字很大）

 **（此题与 剪绳子I 一样，但是数据返回变大了，因此不能用 DP 解决（ O(N*N) 很慢 ））**

 */
public class S014_2 {
    /**
     * 解法 2，贪婪算法
     *
     * 每次，尽量将绳子剪成长度为 3 的段，但如果发现剩余长度为 4 的话，那么，不应该再剪了
     */
    private static final int MOD = 1000000007;

    public int cuttingRope(int n) {
        if (n <= 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }

        // 长度大于 3 时，尽可能的每次都减成 长度为 3 的段  （但如果剩下为 4，就不应该再减了）
        int timesOf3 = n / 3;
        // 说明上一次减3 时，剩下的为 4，得退 1 次
        if (n - (timesOf3 * 3) == 1) {
            timesOf3--;
        }
        int leftLen = n - timesOf3 * 3; // 注意，剩下的为 0 时，不能乘了
        // 正常这么做应该没问题，但是 leetcode 系统不是这么判断的，得每次运算都取模
    //    long product = (long)Math.pow(3, timesOf3) * (leftLen > 1? leftLen : 1);
    //    return (int)(product % MOD);

        long product = 1;
        for (int i = 0; i < timesOf3; i++) {
            product *= 3;
            product %= MOD;
        }
        if (leftLen > 0) {
            product *= leftLen;
            product %= MOD;
        }

        return (int)product;
    }

    @Test
    public void test() {
        Assert.assertEquals(387420489, cuttingRope(54));
    }

}
