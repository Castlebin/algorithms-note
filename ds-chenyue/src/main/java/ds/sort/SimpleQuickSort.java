package ds.sort;

import java.util.Arrays;

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

    public static void main(String[] args) {
        int[] xxx = new int[]{9,8,7,6,5,4,3,2};
        sort(xxx);
        System.out.println(Arrays.toString(xxx));
    }

}
