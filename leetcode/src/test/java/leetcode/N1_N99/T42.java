package leetcode.N1_N99;

import org.junit.Assert;
import org.junit.Test;

/**
 * 42. 接雨水
 */
public class T42 {
    public int trap(int[] height) {
        int N = height.length;
        int[] leftMax = new int[N]; // leftMax[i] 代表 height[0..i] 中的最大值
        leftMax[0] = height[0];
        int[] rightMax = new int[N];// rightMax[i] 代表 height[i..N-1] 中的最大值
        rightMax[N - 1] = height[N - 1];

        // 前缀和的思想。每个位置，它所能产生的最大储水量，取决于它左边的最大值、右边的最大值、它自身的值
        for (int left = 1, right = N - 2; left < N && right >= 0; left++, right--) {
            leftMax[left] = Math.max(leftMax[left - 1], height[left]);
            rightMax[right] = Math.max(rightMax[right + 1], height[right]);
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return sum;
    }

    @Test
    public void test() {
        Assert.assertEquals(6, trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        Assert.assertEquals(9, trap(new int[] {4, 2, 0, 3, 2, 5}));
    }
}
