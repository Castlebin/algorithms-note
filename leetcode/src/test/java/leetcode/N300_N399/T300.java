package leetcode.N300_N399;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 300. 最长递增子序列
 */
public class T300 {

    /**
     * 使用二分查找解法，算法复杂度: O(NlogN)
     **/
    public int lengthOfLIS_3(int[] nums) {
        List<Integer> top = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int pos = findFirstGreatThanOrEqualTo(top, nums[i]);
            if (pos == -1) { // 没找到，说明要新起一堆
                top.add(nums[i]);
            } else { // 找到了，将它放入这堆的堆顶
                top.set(pos, nums[i]);
            }
        }
        return top.size();
    }
    // nums是一个递增的排序数组，在 nums 中查找第一个 大于等于 target 的位置。没有的话返回 -1 （也可以选择返回 nums.size() ，在使用返回值的时候，自己注意判断好就行）
    public int findFirstGreatThanOrEqualTo(List<Integer> nums, int target) {
        int left = 0, right = nums.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) >= target) { // 当前 mid 是一个满足条件的位置
                if (mid == 0 || nums.get(mid - 1) < target) {
                    return mid;
                } else {
                    // 还可以往左边移动
                    right = mid - 1;
                }
            } else if (nums.get(mid) < target) {
                // 说明要再往右边去找，所以，将 left 往右移
                left = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 最典型的 动态规划 解法，算法复杂度：O(N^2)
     */
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

    /**
     * 思考：如果定义 dp[i] 为到 nums[i] 处最长的递增子序列的长度，是否可以。( 这样结果直接是是返回 dp[N - 1] )
     */
    public int lengthOfLIS_2(int[] nums) {
        int N = nums.length;
        // 定义 dp[i] 为到 nums[i] 处最长的递增子序列的长度
        int[] dp = new int[N];
        // 同样的道理，dp[i] 都可以先赋值为 1
        Arrays.fill(dp, 1);

        // 看一下是否可以 （尝试推导一下）
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 发现不行，因为这个时候其实没法决定 nums[i] 能否加入成为一个更长的子序列
                    // !!不能确定递推公式！！失败
                }
            }
        }

        // 直接返回 dp[N - 1]
        return dp[N - 1];
    }

    @Test
    public void test() {
        Assert.assertEquals(4, lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        Assert.assertEquals(4, lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
        Assert.assertEquals(1, lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}));

        Assert.assertEquals(4, lengthOfLIS_3(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        Assert.assertEquals(4, lengthOfLIS_3(new int[]{0, 1, 0, 3, 2, 3}));
        Assert.assertEquals(1, lengthOfLIS_3(new int[]{7, 7, 7, 7, 7, 7, 7}));
    }
    
}
