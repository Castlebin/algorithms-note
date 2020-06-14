package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 面试题14- II. 剪绳子
 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

 提示：
    2 <= n <= 1000

 （此题与 剪绳子I 一样，但是数据返回变大了，因此不能用 DP 解决）
 */
public class S014_2 {
    /**
     * 解法 2，贪婪算法
     *
     * 每次，尽量将绳子剪成长度为 3 的段，如果发现剩余长度为 4 的话，那么，剪成两段 长度为 2 的
     */
    private static final int MOD = 1000000007;

    public int cuttingRope(int ropeLength) {
        if (ropeLength < 2) {
            return 0;
        }
        if (ropeLength == 2) {
            return 1;
        }
        if (ropeLength == 3) {
            return 2;
        }
        if (ropeLength == 4) {
            return 4;
        }

        int timesOf3 = ropeLength / 3;
        // 当剩下长度为 4 时，不应该减去 3
        if (ropeLength - timesOf3 * 3 == 1) {
            timesOf3 = timesOf3 - 1;
        }

        int usedLength = timesOf3 * 3;
        // 剩下的，只可能是 4、2、0 了，非0的话直接用，不用再减
        int leftLength = ropeLength - usedLength;

        long product = 1;
        for (int i = 0; i < timesOf3; i++) {
            product *= 3;
            if (product >= MOD) {
                product = product % MOD;
            }
        }
        if (leftLength > 0) {
            product *= leftLength;
            if (product >= MOD) {
                product = product % MOD;
            }
        }

        return (int)product;
    }

    @Test
    public void test() {
        Assert.assertEquals(36, cuttingRope(10));
    }

}
