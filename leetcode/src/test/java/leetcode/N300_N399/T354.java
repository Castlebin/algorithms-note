package leetcode.N300_N399;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 354. 俄罗斯套娃信封问题
 */
public class T354 {

    /**
     * 排序，然后转化为求最长递增子序列长度的问题
     * 用二分查找来求最长递归子序列的长度
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }

        // 排序。宽度小的排在前面，如果宽度相同，高度小的排在后面 (因为同样宽度的信封没法嵌套，这样保证同样宽度的信封只会取一次)
        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] == e2[0]) { // 宽度相同
                return e2[1] - e1[1]; // 高度小的排在后面
            }
            return e1[0] - e2[0]; // 宽度小的排在前面
        });

        // 对高度进行求最长递增子序列的最大值
        int N = envelopes.length;
        List<Integer> top = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int height = envelopes[i][1];
            // 使用二分查找在 top 列表中寻找插入 height 值的位置。即查找第一个大于等于 height 的数字的位置 (左侧)
            int pos = findFirstGreatOrEqualThan(top, height);
            if (pos == -1) { // 没找到，说明应该新起一堆
                top.add(height);
            } else {
                top.set(pos, height); // 更新这个位置的值为 height，表示将这个值放入了这一堆牌的堆顶了
            }
        }
        return top.size();
    }

    // nums是一个递增的排序数组，在 nums 中查找第一个 大于等于 target 的位置。没有的话返回 -1 （也可以选择返回 nums.size() ，在使用返回值的时候，自己注意判断好就行）
    public int findFirstGreatOrEqualThan(List<Integer> nums, int target) {
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
     * 排序，然后转化为求最长递增子序列长度的问题
     * 传统的求最长递增子序列的解法在这里会超时 （ 时间复杂度 O(N^2) ）
     */
    public int maxEnvelopes_1(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }

        // 排序。宽度小的排在前面，如果宽度相同，高度小的排在后面 (因为同样宽度的信封没法嵌套，这样保证同样宽度的信封只会取一次)
        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] == e2[0]) { // 宽度相同
                return e2[1] - e1[1]; // 高度小的排在后面
            }
            return e1[0] - e2[0]; // 宽度小的排在前面
        });

        // 对高度进行求最长递增子序列的最大值
        int N = envelopes.length;
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) { // 信封 i 的高度比信封 j 的高度大
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, maxEnvelopes_1(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
        Assert.assertEquals(1, maxEnvelopes_1(new int[][]{{1, 1}, {1, 1}, {1, 1}}));

        Assert.assertEquals(3, maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
        Assert.assertEquals(1, maxEnvelopes(new int[][]{{1, 1}, {1, 1}, {1, 1}}));
    }

}
