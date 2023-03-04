package leetcode.N1_N99;

import org.junit.Assert;
import org.junit.Test;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 */
public class T34 {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0
                || nums[0] > target || nums[nums.length - 1] < target) {
            return new int[] {-1, -1};
        }
        int left = leftBound(nums, target);
        int right = rightBound(nums, target);
        return new int[] {left, right};
    }

    // 寻找左边界
    public int leftBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                if (mid == 0 || nums[mid - 1] < target) {
                    return mid;
                } else {
                    // 说明这时候，mid - 1 >= 0 ，并且 nums[mid - 1] == target ，所以可以将右边界缩小
                    right = mid - 1;
                }
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    // 寻找右边界
    public int rightBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                if ((mid == nums.length - 1) || (nums[mid + 1] > target)) {
                    return mid;
                } else {
                    // 说明这时候 mid + 1 <= nums.length - 1 ，并且 nums[mid + 1] == target，所以可以将左边界缩小
                    left = mid + 1;
                }
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] {3, 4}, searchRange(new int[] {5, 7, 7, 8, 8, 10}, 8));
        Assert.assertArrayEquals(new int[] {-1, -1}, searchRange(new int[] {5, 7, 7, 8, 8, 10}, 6));
    }

}
