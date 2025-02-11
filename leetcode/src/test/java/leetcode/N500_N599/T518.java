package leetcode.N500_N599;

import org.junit.Test;
import org.junit.Assert;

/**
 * 518. 零钱兑换 II
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请计算并返回可以凑成总金额的硬币组合数。假设每种面额的硬币有无限个。
 * 
 * 解题思路：
 * 1. 定义 dp[i][j] 为仅使用前 i 种硬币，能够凑成总金额 j 的组合数
 * 2. 状态转移方程：
 *    - 不使用当前硬币：dp[i][j] = dp[i - 1][j]
 *    - 使用当前硬币：dp[i][j] = dp[i][j - coins[i - 1]]
 * 3. 初始化：
 *    - dp[0][0] = 1：金额为 0 时，有一种解法（什么都不选）
 *    - 其他 dp[0][j] = 0：金额为正数时，没有解法
 * 4. 计算顺序：
 *    - 先遍历硬币，再遍历金额
 */
public class T518 {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // 定义 dp[i][j] 为仅使用前 i 种硬币，能够凑成总金额 j 的组合数
        int[][] dp = new int[n + 1][amount + 1];
        // base case . 金额 0 ，都是有一种解法的，也就是不用凑
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        // 另外，如果硬币数为 0 ，金额不为 0，那么无解。都是 0 ，所以不用初始化
        // 计算每个 dp[i][j]
        for (int i = 1; i <= n; i++) {
            int coin = coins[i - 1]; // 第 i 种硬币
            for (int j = 0; j <= amount; j++) {
                // 1. 不使用这种硬币
                dp[i][j] = dp[i - 1][j];
                // 2. 使用这种硬币
                if (j - coin >= 0) {
                    dp[i][j] += dp[i][j - coin];
                }
            }
        }
        return dp[n][amount];
    }

    @Test
    public void testBasicCases() {
        // 基本测试用例
        Assert.assertEquals(4, change(5, new int[]{1, 2, 5}));
        Assert.assertEquals(0, change(3, new int[]{2}));
        Assert.assertEquals(1, change(10, new int[]{10}));
    }
    
    @Test
    public void testSpecialCases() {
        // 特殊情况测试
        Assert.assertEquals(1, change(0, new int[]{1, 2}));     // 金额为 0
        Assert.assertEquals(0, change(5, new int[]{}));         // 空硬币数组
        Assert.assertEquals(0, change(4, new int[]{5}));        // 最小面额大于目标金额
    }
    
    @Test
    public void testLargeNumbers() {
        // 较大数值测试
        Assert.assertEquals(0, change(10000, new int[]{10001}));  // 所有硬币都太大
        Assert.assertEquals(1, change(100, new int[]{100}));      // 刚好一个大硬币
    }

}
