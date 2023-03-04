package binarySearch;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import common.NumUtil;

/**
 * 最正统的二分搜索
 * 在正排序数组中搜索等于 target 的元素的位置，不存在的话返回 -1
 */
public class BinarySearch {

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
        for (int testCount = 0; testCount < 1000; testCount++) {
            // 生成测试数据
            int N = new Random().nextInt(10000);
            int[] nums = NumUtil.generateRandomArray(N, 0, 7000);
            int target = NumUtil.generateRandomArray(1, 0, 7000)[0];
            Arrays.sort(nums);

            // 开始测试
            int directSearchResult = directSearch(nums, target);
            int binarySearchResult = search(nums, target);
            if (directSearchResult == -1 && binarySearchResult == -1) {
                continue;
            }
            if (directSearchResult == -1 && binarySearchResult != -1) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("target = " + target);
                System.out.println("directSearchResult = " + directSearchResult);
                System.out.println("binarySearchResult = " + binarySearchResult);
                continue;
            }
            if (nums[directSearchResult] != target || nums[binarySearchResult] != target) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("target = " + target);
                System.out.println("directSearchResult = " + directSearchResult);
                System.out.println("binarySearchResult = " + binarySearchResult);
            }
        }
    }

    private int directSearch(int[] inOrderNums, int target) {
        for (int i = 0; i < inOrderNums.length; i++) {
            if (inOrderNums[i] == target) {
                return i;
            } else if (inOrderNums[i] > target) {
                return -1;
            }
        }
        return -1;
    }

}
