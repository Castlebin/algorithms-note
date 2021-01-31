package interview;

import common.ArrayUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 从一个排序数组中，找到小于等于目标数的数字个数
 */
public class N210131 {

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            int[] nums = ArrayUtil.generateNonNegativeArray(i, 52);
            Arrays.sort(nums);
            System.out.println(Arrays.toString(nums));
            Assert.assertEquals(findMaxLessThanCountsTest(nums, 37),
                    findMaxLessThanCounts(nums, 37));
        }
    }

    public int findMaxLessThanCounts(int[] sortedNums, int target) {
        if (sortedNums == null
                || sortedNums.length == 0
                || sortedNums[0] > target) {
            return 0;
        }
        if (sortedNums[sortedNums.length - 1] <= target) {
            return sortedNums.length;
        }
        // 到了这里， sortedNums 的第 0 号位元素一定 小于等于 target，
        // 并且 最后一个元素一定大于 target
        // 也即是 sortedNums 此时 长度 一定大于等于 2
        int begin = 0, end = sortedNums.length - 1;
        int middle = 0;
        while (begin <= end) {
            middle = begin + (end - begin) / 2;
            if (sortedNums[middle] > target
                    && sortedNums[middle - 1] <= target) {
                break;
            }
            if (sortedNums[middle] > target) {
                end = middle - 1;
            } else {
                begin = middle + 1;
            }
        }
        return middle;
    }


    public int findMaxLessThanCountsTest(int[] sortedNums, int target) {
        int count = 0;
        for (int num : sortedNums) {
            if (num <= target) {
                count++;
            }
        }
        return count;
    }

}
