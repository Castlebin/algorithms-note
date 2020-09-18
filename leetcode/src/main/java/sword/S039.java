package sword;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 剑指 Offer 39. 数组中出现次数超过一半的数字
 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

 你可以假设数组是非空的，并且给定的数组总是存在多数元素。

 示例 1:
 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 输出: 2


 限制：

 1 <= 数组长度 <= 50000
 */
public class S039 {

    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("输入数据有误！");
        }
        quickSortForMajorityElement(nums, 0, nums.length - 1);
        return nums[nums.length / 2];
    }

    private static void quickSortForMajorityElement(int[] arr, int start, int end) {
        // 边界判断
        if (start >= end) {
            return;
        }

        int pivotIndex = partition(arr, start, end);
        if (pivotIndex == arr.length / 2) {
            // 说明已经能够确定元素了
            return;
        }
        // 边界判断
        if (pivotIndex > start && pivotIndex > arr.length / 2) {
            quickSortForMajorityElement(arr, start, pivotIndex - 1);
        }
        if (pivotIndex < end && pivotIndex < arr.length / 2) {
            quickSortForMajorityElement(arr, pivotIndex + 1, end);
        }
    }

    /**
     * 注意，上面调用 partition 时，保证了 start < end
     */
    private static int partition(int[] arr, int start, int end) {
        int pivotIndex = getPivotIndex(arr, start, end);

        // [start, end] 区间只有两个元素时
        if (end - start == 1) {
            if (arr[start] > arr[end]) {
                swap(arr, start, end);
            }
            return start;
        }

        // [start, end] 区间，有三个或以上元素
        int pivot = arr[pivotIndex];
        int rightIndex = end;
        // 把 pivot 放到 右边 藏起来
        swap(arr, pivotIndex, rightIndex);
        while (start < end) {
            while (start <= end && arr[start] < pivot) {
                start++;
            }
            while (start <= end && arr[end] >= pivot) {
                end--;
            }
            if (start < end) {
                swap(arr, start, end);
            }
        }
        // 此时，start 位置，应该是主元应该待的位置
        if (start < rightIndex) {
            swap(arr, start, rightIndex);
        }
        return start;
    }

    /**
     * 这里选用的算法，可以随意切换，比如使用 三元取中值 或者 随机取值
     *
     * 注意 上面的 quickSort 第一步，已经保证了 start < end，无需判断了
     */
    private static int getPivotIndex(int[] arr, int start, int end) {
        return middleThree(arr, start, end);
    }

    /**
     * 三元取中 !! 别写错了
     */
    private static int middleThree(int[] arr, int start, int end) {
        int middle = (end - start) / 2 + start;
        if ((arr[start] <= arr[middle] && arr[middle] <= arr[end])
                || (arr[start] >= arr[middle] && arr[middle] >= arr[end])) {
            return middle;
        } else if ((arr[middle] <= arr[start] && arr[start] <= arr[end])
                || (arr[middle] >= arr[start] && arr[start] >= arr[end])) {
            return start;
        } else {
            return end;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{3, 3, 4};
        Assert.assertEquals(3, majorityElement(nums));

        nums = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};
        Assert.assertEquals(2, majorityElement(nums));
    }

}
