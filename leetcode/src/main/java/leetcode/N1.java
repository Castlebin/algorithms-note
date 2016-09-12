package leetcode;

import java.util.Arrays;

public class N1 {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    result[0] = i;
                    result[1] = j;

                    return result;
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4};
        int target = 6;

        int[] result = new N1().twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }

}
