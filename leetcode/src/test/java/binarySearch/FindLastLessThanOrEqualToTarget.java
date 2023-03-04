package binarySearch;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import common.NumUtil;

/**
 * 在正排序数组中，寻找最后一个 小于等于 target 的元素的位置，不存在的话返回 -1  (右边界、变种)
 */
public class FindLastLessThanOrEqualToTarget {

    public int findLastLessThanOrEqualToTargetPos(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // 防止溢出的经典做法
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                // mid 可能是答案，但是不一定
                if (mid == nums.length - 1 || nums[mid + 1] > target) {
                    // 说明 mid 就是答案
                    return mid;
                } else {
                    // 说明 mid 不是答案，但是 mid 右边的元素可能是答案
                    left = mid + 1;
                }

            } else if (nums[mid] > target) {
                // 说明 mid 太大了，需要往左边找
                right = mid - 1;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        for (int testCount = 0; testCount < 10; testCount++) {
            // 生成测试数据
            int N = new Random().nextInt(10000);
            int[] nums = NumUtil.generateRandomArray(N, 0, 7000);
            int target = NumUtil.generateRandomArray(1, 0, 7000)[0];
            Arrays.sort(nums);

            // 开始测试
            int directSearchResult = directSearch(nums, target);
            int binarySearchResult = findLastLessThanOrEqualToTargetPos(nums, target);
            if (directSearchResult == -1 && binarySearchResult == -1) {
                continue;
            }
            if (directSearchResult == -1 && binarySearchResult != -1) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("target = " + target);
                System.out.println("directSearchResult = " + directSearchResult);
                System.out.println("binarySearchResult = " + binarySearchResult);
                break;
            }
            if (directSearchResult != binarySearchResult) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("target = " + target);
                System.out.println("directSearchResult = " + directSearchResult);
                System.out.println("binarySearchResult = " + binarySearchResult);
                break;
            }
        }
    }

    private int directSearch(int[] inOrderNums, int target) {
        int pos = -1;
        for (int i = 0; i < inOrderNums.length; i++) {
            if (inOrderNums[i] <= target) {
                pos = i;
            } else {
                break;
            }
        }
        return pos;
    }

}
