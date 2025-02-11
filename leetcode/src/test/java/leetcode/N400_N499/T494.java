package leetcode.N400_N499;

import org.junit.Assert;
import org.junit.Test;

/**
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target。
 * 向数组中的每个整数前添加 '+' 或 '-'，然后串联起所有整数，可以构造一个表达式。
 * 
 * 解题思路：
 * 1. 将数组分成两部分 P（正数）和 N（负数），使得 sum(P) - sum(N) = target
 * 2. 已知：sum(P) + sum(N) = sum, sum(P) - sum(N) = target
 * 3. 可得：sum(P) = (target + sum) / 2
 * 4. 问题转化为：从数组中选择一些数，使其和为 (target + sum) / 2
 */
public class T494 {
    public int findTargetSumWays(int[] nums, int target) {
        // 处理空数组的情况
        if (nums == null || nums.length == 0) {
            return target == 0 ? 1 : 0;
        }
        
        // 计算数组总和
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        // 判断是否可能达到目标值
        if (Math.abs(target) > sum || (sum + target) % 2 != 0) {
            return 0;
        }
        
        // 要找的正数和
        int positiveSum = (int) ((sum + target) / 2);
        
        // dp[i][j] 表示：使用前 i 个数，凑成和为 j 的方案数
        int[][] dp = new int[nums.length + 1][positiveSum + 1];
        
        // 初始化：空数组凑成和为 0 的方案数为 1
        dp[0][0] = 1;
        
        // 遍历每个数字
        for (int i = 1; i <= nums.length; i++) {
            int num = nums[i - 1];
            // 遍历每个可能的和
            for (int j = 0; j <= positiveSum; j++) {
                // 不选当前数字
                dp[i][j] = dp[i - 1][j];
                
                // 选当前数字（如果和足够大）
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        
        return dp[nums.length][positiveSum];
    }

    @Test
    public void test() {
        // 基本测试
        Assert.assertEquals(5, findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        Assert.assertEquals(1, findTargetSumWays(new int[]{1}, 1));
        
        // 包含 0 的测试
        Assert.assertEquals(2, findTargetSumWays(new int[]{1, 0}, 1));
        
        // 无解的情况
        Assert.assertEquals(0, findTargetSumWays(new int[]{1}, 2));
        Assert.assertEquals(0, findTargetSumWays(new int[]{1, 2, 3, 4, 5}, 20));
        
        // 边界情况
        Assert.assertEquals(1, findTargetSumWays(new int[]{}, 0));    // 空数组，目标为 0
        Assert.assertEquals(0, findTargetSumWays(new int[]{}, 1));    // 空数组，目标非 0
        Assert.assertEquals(1, findTargetSumWays(new int[]{100}, -100)); // 大数值测试
    }
}
