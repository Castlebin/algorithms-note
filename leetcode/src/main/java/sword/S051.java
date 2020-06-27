package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 51. 数组中的逆序对

 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 输入一个数组，求出这个数组中的逆序对的总数。
*/
public class S051 {
    public int reversePairs(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        return sort(nums);
    }

    public int sort(int[] nums) {
        int[] tmp = new int[nums.length];
        int reverseCount = mergeSort(nums, tmp,0, nums.length - 1);
        return reverseCount;
    }

    public int mergeSort(int[] nums, int[] tmp, int leftBegin, int rightEnd) {
        int reverseCount = 0;
        if (leftBegin < rightEnd) {
            // 防溢出的求中间元素的方法
            int center = leftBegin + (rightEnd - leftBegin) / 2;
            reverseCount += mergeSort(nums, tmp, leftBegin, center);
            reverseCount += mergeSort(nums, tmp, center + 1, rightEnd);
            reverseCount += merge(nums, tmp, leftBegin, center + 1, rightEnd);
        }
        return reverseCount;
    }

    /**
     *  核心算法，两个有序数组的归并
     */
    public int merge(int[] nums, int[] tmp, int leftBegin, int rightBegin, int rightEnd) {
        int reverseCount = 0;
        int leftEnd = rightBegin - 1;
        int p1 = leftBegin;
        int p2 = rightBegin;
        int i = leftBegin;
        while (p1 <= leftEnd && p2 <= rightEnd) {
            if (nums[p1] <= nums[p2]) {
                tmp[i++] = nums[p1++];
            } else {
                reverseCount += (leftEnd - p1 + 1);

                tmp[i++] = nums[p2++];
            }
        }
        while (p1 <= leftEnd) {
            tmp[i++] = nums[p1++];
        }
        while (p2 <= rightEnd) {
            tmp[i++] = nums[p2++];
        }

        // 将tmp中排序好的元素，倒回原数组
        for (i = leftBegin; i <= rightEnd; i++) {
            nums[i] = tmp[i];
        }

        return reverseCount;
    }

    @Test
    public void test() {
        Assert.assertEquals(5, reversePairs(new int[]{7,5,6,4}));
    }

}
