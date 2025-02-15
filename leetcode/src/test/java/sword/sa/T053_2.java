package sword.sa;

import org.junit.Assert;
import org.junit.Test;

/**
 * 剑指 Offer 53 - II. [0～n-1] 中缺失的数字
 * 一个长度为 n-1 的 *递增排序数组* 中的所有数字都是唯一的，并且每个数字都在范围 [0～n-1] 之内。
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/que-shi-de-shu-zi-lcof
 */
public class T053_2 {

    // 注意，数组是 递增排序数组，所以需要用到这个特性（二分查找即可）
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 左边界或者右边界的话，特殊处理一下（可以直接判断出来，速度也快，都不用二分查找）
        if (nums[0] != 0) {// 缺失的是 0
            return 0;
        }
        if (nums[nums.length - 1] != nums.length) {// 缺失的是 N-1
            return nums.length;
        }
        // 缺失的是中间的某个数
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == mid) {// 很好，在自己该待的位置上。所以，应该去右边找
                left = mid + 1;
            } else if (nums[mid] < mid) {// 说明缺的数在左边，去左边找
                right = mid - 1;
            } else {// nums[mid] > mid , 说明在左边，或者就是自己
                if (mid == 0 || nums[mid - 1] == mid - 1) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, missingNumber(new int[] {0, 1, 3}));
        Assert.assertEquals(8, missingNumber(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 9}));
    }

}
