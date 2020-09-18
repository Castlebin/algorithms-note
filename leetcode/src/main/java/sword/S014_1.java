package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 面试题14- I. 剪绳子
 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 若干段，总段数记为 m（m、n都是整数，n>1并且m>1），
 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

 提示：
    2 <= n <= 58          （可以看到数字较小）
 */
public class S014_1 {
    /**
     * 解法 1，动态规划
     */
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
        // 动态规划，存下每一个小问题的最优解，避免递归计算 (从上到下分析问题，从下到上解决问题)
        // 注意 这里存下的初始值，并不是 相应长度时的最优解，而是，他们作为一个单独的段时，能够贡献的乘积的最大值
        int[] products = new int[n + 1];
        products[0] = 0;
        products[1] = 1;    // 不是 0
        products[2] = 2;    // 不是 1
        products[3] = 3;    // 不是 2

        // 接下来，开始计算 绳子长度 为 4 -> n ，每个最优解
        for (int ropeLen = 4; ropeLen <= n; ropeLen++) {
            int max = 0;
            // 假设 第一次 将 绳子减为 firstCutLen 、ropeLen - firstCutLen 两段  （因为 段的长度大于 1 才有意义，注意看设值范围）
            for (int firstCutLen = 2; firstCutLen < ropeLen - 1; firstCutLen++) {
                int product = products[firstCutLen] * products[ropeLen - firstCutLen];
                if (product > max) {
                    max = product;
                }
            }
            products[ropeLen] = max;
        }
        return products[n];
    }

    @Test
    public void test() {
        Assert.assertEquals(36, cuttingRope(10));
    }

}
