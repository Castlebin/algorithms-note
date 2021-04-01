package sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 用快速排序的思路找到数组中的第 K 大 的数

 输入保证有解
 */
public class QuickSortForTopK {

    int ansIndex = -1;

    public int topK(int[] arr, int k) {
        quickSortForTopK(arr, k, 0, arr.length - 1);
        if (ansIndex != -1) {
            return arr[ansIndex];
        }
        return -1;
    }

    private void quickSortForTopK(int[] arr, int k, int begin, int end) {
        int pivotIndex = partition(arr, begin, end);

        if (arr.length == k + pivotIndex) {
            ansIndex = pivotIndex;
            return;
        }

        if (pivotIndex > begin) {
            quickSortForTopK(arr, k, begin, pivotIndex - 1);
        }
        if (pivotIndex < end) {
            quickSortForTopK(arr, k, pivotIndex + 1, end);
        }
    }

    private int partition(int[] arr, int begin, int end) {
        int left = begin, right = end;
        int pivotIndex = left;
        int pivot = arr[pivotIndex];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            if (left < right) {
                swap(arr, left, right);
            }
        }
        swap(arr, right, pivotIndex);
        return right;
    }

    public void swap(int[] arr, int p1, int p2) {
        if (p1 != p2 && arr[p1] != arr[p2]) {
            int temp = arr[p1];
            arr[p1] = arr[p2];
            arr[p2] = temp;
        }
    }

    @Test
    public void testTopK() {
        int[] arr = new int[]{-10, 9, 6, 12, 4, -3, 5};
        arr = ArrayUtil.generateArray(7, 20);
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        Assert.assertEquals(copy[copy.length - 2], topK(arr, 2));
    }

}
