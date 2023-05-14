package sword.sa;

import org.junit.Assert;
import org.junit.Test;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 *
 * 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。
 * 请返回旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
 *
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 */
public class T011 {

    public int minArray(int[] nums) {
        // 直接能判断的，没有旋转过 （优化 1）
        if (nums[0] < nums[nums.length - 1]) {
            return nums[0];
        }
        // 还是已经排序的一个数组 (优化 2 )
        boolean orderd = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                orderd = false;
                break;
            }
        }
        if (orderd) {
            return nums[0];
        }

        // 开始二分查找
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid < nums.length - 1 && nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            if (nums[mid] > nums[right]) {// mid 位置的数 比右边的大，说明 mid 还是属于左边
                left = mid + 1;
            } else if (nums[mid] < nums[left]) {// 说明这个 mid 属于右边
                right = mid - 1;
            } else if (nums[mid] == nums[right]) {
                right = right - 1;
            } else if (nums[mid] == nums[left]) {
                left = left + 1;
            }
        }
        return nums[0];
    }

    class Solution {
        public int minArray(int[] numbers) {
            if (numbers == null || numbers.length == 0) {
                throw new RuntimeException("输入不正确");
            }
            int begin = 0, end = numbers.length - 1;
            if (numbers[begin] < numbers[numbers.length - 1]) {
                return numbers[begin];
            }
            while (begin < end - 1) {
                int mid = (end - begin) / 2 + begin;
                // 三个数字都相同时，*退化为顺序查找* （如果是同一个位置，则表明找到了）
                if (numbers[begin] == numbers[mid]
                        && numbers[mid] == numbers[end]) {
                    return findMinInOrder(numbers, begin, end);
                } else if (numbers[mid] >= numbers[begin]) {
                    begin = mid;
                } else if (numbers[mid] <= numbers[end]) {
                    end = mid;
                }
            }

            return numbers[end];
        }

        private int findMinInOrder(int[] numbers, int begin, int end) {
            int min = numbers[0];
            for (int i = begin; i <= end; i++) {
                if (numbers[i] < min) {
                    min = numbers[i];
                }
            }
            return min;
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(1, minArray(new int[] {3, 4, 5, 1, 2}));
        Assert.assertEquals(0, minArray(new int[] {2, 2, 2, 0, 1}));

        Assert.assertEquals(1, new Solution().minArray(new int[] {3, 4, 5, 1, 2}));
        Assert.assertEquals(0, new Solution().minArray(new int[] {2, 2, 2, 0, 1}));
    }

}
