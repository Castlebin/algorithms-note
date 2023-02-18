package leetcode.N100_N199;

import org.junit.Assert;
import org.junit.Test;


/**
 * 167. Two Sum II - Input Array Is Sorted
 * 167. 两数之和 II - 输入有序数组
 */
public class T167 {

    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length <= 1) {
            return new int[] {-1, -1};
        }

        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                // 注意，题目要求的返回 index 的范围为 [1, numbers.length] ，即从 1 开始，而不是从 0 开始计数
                return new int[] {left + 1, right + 1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }

        // 没有答案的默认返回
        return new int[] {-1, -1};
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] {1, 2}, twoSum(new int[] {2, 7, 11, 15}, 9));
        Assert.assertArrayEquals(new int[] {1, 3}, twoSum(new int[] {2, 3, 4}, 6));
        Assert.assertArrayEquals(new int[] {1, 2}, twoSum(new int[] {-1, 0}, -1));
    }

}
