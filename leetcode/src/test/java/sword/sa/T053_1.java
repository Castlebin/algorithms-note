package sword.sa;

import org.junit.Assert;
import org.junit.Test;

/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 *
 * 统计一个数字在 排序数组 中出现的次数。
 */
public class T053_1 {

    // 二分变体，找左边界和右边界
    public int search(int[] nums, int target) {
        // 分别寻找左边界和右边界，计算一下就可以了
        // 先找一下左边界
        int leftIndex = searchLeftIndex(nums, target);
        if (leftIndex == -1) {// 没有这个数
            return 0;
        }
        // 只有一个这个数
        if (leftIndex < nums.length - 1 && nums[leftIndex + 1] > target) {
            return 1;
        }
        // 找右边界
        int rightIndex = searchRightIndex(nums, target);
        return rightIndex - leftIndex + 1;
    }

    /**
     * 找左边界
     */
    int searchLeftIndex(int[] nums, int target) {
        int begin = 0, end = nums.length - 1;
        while (begin <= end) {
            int mid = begin + (end - begin) / 2;
            if (nums[mid] == target) {
                if ((mid > 0 && nums[mid - 1] < target) || mid == 0) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 找右边界
     */
    int searchRightIndex(int[] nums, int target) {
        int begin = 0, end = nums.length - 1;
        while (begin <= end) {
            int mid = begin + (end - begin) / 2;
            if (nums[mid] == target) {
                if ((mid < nums.length - 1 && nums[mid + 1] > target) || mid == nums.length - 1) {
                    return mid;
                } else {
                    begin = mid + 1;
                }
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, search(new int[] {5, 7, 7, 8, 8, 10}, 8));
        Assert.assertEquals(0, search(new int[] {5, 7, 7, 8, 8, 10}, 6));
    }

}
