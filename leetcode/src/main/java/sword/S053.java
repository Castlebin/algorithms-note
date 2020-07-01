package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 53 - II. 0～n-1中缺失的数字

 一个长度为n-1的 **递增排序** 数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。

 递增数组，所以用二分法查找
*/
public class S053 {
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0 || nums[0] != 0) {
            return 0;
        }
        if (nums[nums.length - 1] != nums.length) {
            return nums.length;
        }
        for (int begin = 0, end = nums.length - 1; begin <= end; ) {
            int middle = begin + (end - begin) / 2;
            if (nums[middle] == middle && nums[middle + 1] > middle + 1) {
                return middle + 1;
            } else if (nums[middle] == middle && nums[middle + 1] == middle + 1) {
                begin = middle + 1;
            } else if (nums[middle] > middle) {
                end = middle - 1;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(8, missingNumber(new int[]{0,1,2,3,4,5,6,7,9}));
    }

}
