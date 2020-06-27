package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 57. 和为s的两个数字

 输入一个 **递增排序** 的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。
 如果有多对数字的和等于s，则输出任意一对即可。
*/
public class S057 {
    public int[] twoSum(int[] sortedNums, int target) {
        // 第一步，寻找第一个 大于等于 target / 2 的数 ，假设index 为 i（如果没有，则表示不存在这种和）
        int minMaxThan = findMinMaximumThanOrEquals(sortedNums, target / 2);
        if (minMaxThan == -1) {
            return new int[]{};
        }

        for (int index = minMaxThan; index < sortedNums.length; index++) {
            int n1 = sortedNums[index];
            // 在 0~index 之间，二分查找是否存在一个数 target - n1
            int i2 = find(sortedNums, target - n1, 0, index - 1);
            if (i2 != -1) {
                return new int[]{n1, sortedNums[i2]};
            }
        }

        // 第二步
        return new int[]{};
    }

    public int find(int[] sortedNums, int x, int begin, int end) {
        if (sortedNums == null || sortedNums.length == 0) {
            return -1;
        }/*
        int begin = 0;
        int end = sortedNums.length - 1;*/
        while (begin <= end) {
            int middle = begin + (end - begin) / 2;
            if (sortedNums[middle] == x) {
                return middle;
            } else if (sortedNums[middle] > x) {
                end = middle - 1;
            } else {
                begin = middle + 1;
            }
        }

        return -1; // 表示不存在这样的数
    }

    public int findMinMaximumThanOrEquals(int[] sortedNums, int x) {
        if (sortedNums == null || sortedNums.length == 0) {
            return -1;
        }

        int begin = 0;
        int end = sortedNums.length - 1;
        while (begin < end) {
            int middle = begin + (end - begin) / 2;
            if (sortedNums[middle] <= x) {
                if (sortedNums[middle + 1] >= x) {
                    return middle + 1;
                } else {
                    begin = middle;
                }
            } else {
                end = middle;
            }
        }

        return -1; // 表示不存在这样的数
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{7,2}, twoSum(new int[]{2,7,11,15}, 9));
        Assert.assertArrayEquals(new int[]{30,10}, twoSum(new int[]{10,26,30,31,47,60}, 40));
    }

}
