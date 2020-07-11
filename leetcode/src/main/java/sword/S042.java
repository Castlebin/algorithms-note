package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 42. 连续子数组的最大和

 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。
 求所有子数组的和的最大值。

 要求时间复杂度为O(n)。

 题目要求，子数组至少包含一个元素
 */
public class S042 {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxSubArraySum = 0;
        int subArraySum = 0;
        boolean allNeg = true;
        int max = nums[0];
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                allNeg = false;
            }
            if (nums[i] > max) {
                max = nums[i];
            }
            subArraySum += nums[i];
            if (subArraySum > maxSubArraySum) {
                maxSubArraySum = subArraySum;
            } else if (subArraySum < 0) {
                subArraySum = 0;
            }
        }
        return allNeg? max : maxSubArraySum;
    }

    @Test
    public void test() {
        Assert.assertEquals(6, maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

}
