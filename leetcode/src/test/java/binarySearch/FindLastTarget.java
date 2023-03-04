package binarySearch;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import common.NumUtil;

/**
 * 在正排序数组中，寻找最后一个等于 target 的元素的位置，不存在的话返回 -1 (找右边界)
 *
 * 验证方法：LeetCode 34. 在排序数组中查找元素的第一个和最后一个位置
 */
public class FindLastTarget {

    public int findLastTargetPos(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // 防止溢出的经典做法
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 是最大的那个元素了，已经到底了 || 或者下一个元素已经大于 target 了
                if (mid == nums.length - 1 || nums[mid + 1] > target) {
                    return mid;
                } else {
                    // 说明后面还有等于 target 的元素，所以缩小左边界
                    left = mid + 1;
                }
            } else if (nums[mid] > target) {
                // 现在的值大于 target，说明应该在左边，所以缩小右边界
                right = mid - 1;
            } else if (nums[mid] < target) {
                // 现在的值小于 target，说明应该在右边，所以缩小左边界
                left = mid + 1;
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
            int binarySearchResult = findLastTargetPos(nums, target);
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
            if (inOrderNums[i] == target) {
                pos = i;
            } else if (inOrderNums[i] > target) {
                break;
            }
        }
        return pos;
    }

}
