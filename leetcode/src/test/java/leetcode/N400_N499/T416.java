package leetcode.N400_N499;

import org.junit.Assert;
import org.junit.Test;

/**
 * 416. 分割等和子集
 * 给你一个只包含正整数的非空数组 nums，判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 
 * 解题思路：
 * 1. 计算总和，如果是奇数则无法分割
 * 2. 使用二维 dp 数组，dp[i][j] 表示：
 *    - 使用前 i 个数字
 *    - 是否可以恰好凑出和为 j
 * 3. 对于每个数字，可以选择放入或不放入：
 *    - 不放入：dp[i][j] = dp[i - 1][j]
 *    - 放入：dp[i][j] = dp[i - 1][j - nums[i - 1]]
 */
public class T416 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        // 如果是奇数，无法分割
        if (sum % 2 != 0) {
            return false;
        }
        
        int target = sum / 2;
        int n = nums.length;
        
        // dp[i][j]: 使用前 i 个数字，是否可以凑出和为 j
        boolean[][] dp = new boolean[n + 1][target + 1];
        
        // 初始化：空集可以凑出和为 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        
        // 填充 dp 数组
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                // 不选择当前数字
                dp[i][j] = dp[i - 1][j];
                
                // 如果当前数字可以选择，尝试选择它
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        
        return dp[n][target];
    }

    @Test
    public void test() {
        // 可以分割成 [1, 5, 5] 和 [11]
        Assert.assertTrue(canPartition(new int[]{1, 5, 11, 5}));
        // 无法分割成两个相等的子集
        Assert.assertFalse(canPartition(new int[]{1, 2, 3, 5}));
        // 可以分割成 [1, 2, 3] 和 [6]
        Assert.assertTrue(canPartition(new int[]{1, 2, 3, 6}));
        // 可以分割成 [2, 4] 和 [3, 3]
        Assert.assertTrue(canPartition(new int[]{2, 4, 3, 3}));
    }
}
