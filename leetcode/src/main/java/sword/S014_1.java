package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 面试题14- I. 剪绳子
 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

 提示：
     1 <= n,m <= 100
     0 <= k <= 20
 */
public class S014_1 {
    /**
     * 解法 1，动态规划
     */
    public int cuttingRope(int ropeLength) {
        if (ropeLength < 2) {
            return 0;
        }
        if (ropeLength <= 3) {
            return ropeLength - 1;
        }
        int[] products = new int[ropeLength + 1];
        products[0] = 0;
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;

        for (int len = 4; len <= ropeLength; len++) {
            int max = 0;
            for (int j = 2; j <= len / 2; j++) {
                // 即 把长度为 len 的绳子，分为了 长度为 j(j >= 2) 和 len - j 两段
                int product = products[j] * products[len - j];
                if ( product > max) {
                    max = product;
                }
            }
            products[len] = max;
        }
        return products[ropeLength];
    }

    @Test
    public void test() {
        Assert.assertEquals(36, cuttingRope(10));
    }

}
