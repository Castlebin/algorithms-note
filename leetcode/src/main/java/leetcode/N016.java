package leetcode;

import org.junit.Test;

/**
 * 16. 3Sum Closest
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * <p>
 * Example:
 * <p>
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * <p>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class N016 {

    public int threeSumClosest(int[] nums, int target) {
        int c = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int t = nums[i] + nums[j] + nums[k] - target;
                    if (Math.abs(t) < Math.abs(c)) {
                        c = t;
                    }
                }
            }
        }
        return c + target;
    }

    @Test
    public void test() {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1}, -4));
        System.out.println(threeSumClosest(new int[]{1, 1, -1, -1, 3}, 3));
    }

}
