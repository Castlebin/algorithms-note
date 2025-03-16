package leetcode.N1_N99;

import org.junit.Assert;
import org.junit.Test;

/**
 * 33. 搜索旋转排序数组
 *
 * 注意，数组中每个数字都是不同的
 * ** 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。 **
 *
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/
 */
public class T33 {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) { // 刚好找到
                return mid;
            }

            // 先判断左边是否有序
            if (nums[left] <= nums[mid]) {
                // 左边有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // 在左边
                } else {
                    left = mid + 1; // 在右边
                }
            } else {
                // 右边有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // 在右边
                } else {
                    right = mid - 1; // 在左边
                }
            }
        }

        return -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(4, search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        Assert.assertEquals(-1, search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        Assert.assertEquals(0, search(new int[]{1}, 1));
    }

}
