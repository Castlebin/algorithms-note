package leetcode.N700_N799;

import org.junit.Assert;
import org.junit.Test;

/**
 * 704. Binary Search
 * 704. 二分查找
 */
public class T704 {

    /**
     * 最正统的二分查找
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // 防止溢出的经典做法
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
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
        Assert.assertEquals(4, search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
        Assert.assertEquals(-1, search(new int[]{-1, 0, 3, 5, 9, 12}, 2));
    }

}
