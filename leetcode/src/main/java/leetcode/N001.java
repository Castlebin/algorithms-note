package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class N001 {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int b = target - nums[i];
            if (map.containsKey(b)) {
                result[0] = map.get(b);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }

        return null;
    }

    @Test
    public void testTwoSum() {
        int[] nums = new int[] {3, 2, 4};
        int target = 6;

        int[] result = twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }

}
