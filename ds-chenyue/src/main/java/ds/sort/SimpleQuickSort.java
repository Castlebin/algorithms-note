package ds.sort;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**

 最简单容易理解的快速排序
 直接选取左边元素作为主元，而且不用先藏主元
 直接  left， end 作为区间 即可

 */
public class SimpleQuickSort {

    public static void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public static void quickSort(int[] nums, int begin, int end) {
        int pivotIndex = partition(nums, begin, end);
        if (begin < pivotIndex) {
            quickSort(nums, begin, pivotIndex - 1);
        }
        if (end > pivotIndex) {
            quickSort(nums, pivotIndex + 1, end);
        }
    }

    public static int partition(int[] nums, int begin, int end) {
        int left = begin, right = end;
        int pivot = nums[begin];
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            if (left < right) {
                swap(nums, left, right);
            }
        }
        swap(nums, begin, left);
        return left;
    }

    public static void swap(int[] nums, int p1, int p2) {
        if (p1 != p2 && nums[p1] != nums[p2]) {
            int tmp = nums[p1];
            nums[p1] = nums[p2];
            nums[p2] = tmp;
        }
    }

    @Test
    public void test() {
        int[] array1 = new int[] {5, 2, 3, 1};
        sort(array1);
        Assert.assertArrayEquals(new int[] {1, 2, 3, 5}, array1);

        int[] array2 = new int[] {5, 1, 1, 2, 0, 0};
        sort(array2);
        Assert.assertArrayEquals(new int[] {0, 0, 1, 1, 2, 5}, array2);

        int[] array3 = new int[] {-4, 0, 7, 4, 9, -5, -1, 0, -7, -1};
        sort(array3);
        Assert.assertArrayEquals(new int[] {-7, -5, -4, -1, -1, 0, 0, 4, 7, 9}, array3);

        int[] array4 = new int[] {4, 981, 10, -17, 0, -20, 29, 50, 8, 43, -5};
        sort(array4);
        Assert.assertArrayEquals(new int[] {-20, -17, -5, 0, 4, 8, 10, 29, 43, 50, 981}, array4);
    }

    public static void main(String[] args) {
        int[] xxx = new int[] {9, 8, 7, 6, 5, 4, 3, 2};
        sort(xxx);
        System.out.println(Arrays.toString(xxx));
    }

}
