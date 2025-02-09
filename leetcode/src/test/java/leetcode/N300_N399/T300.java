package leetcode.N300_N399;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 300. 最长递增子序列
 */
public class T300 {

    public int lengthOfLIS(int[] nums) {
        int N = nums.length;
        // 典型的动态规划解法。定义 dp[i] 为以 nums[i] 作为结尾的最长递增子序列的长度
        // 因为单个数字本身就满足需求，所以 dp 数组的值全部可以初始化为 1
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) { // 到 j 的子序列，加上 nums[i]，是递增的子序列。所以 dp[i] 可以是 dp[j] + 1
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 1;
        for (int i = 1; i < N; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals(4, lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        Assert.assertEquals(4, lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
        Assert.assertEquals(1, lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}));
    }
    
}
