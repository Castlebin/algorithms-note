package sword.sort;

import common.ArrayUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 一次过？厉害了
 * 有点满意 ^_^
 *
 * 注意 循环时，要判断边界啊！！！（因为这个没一次过 😢）
 *
 * 简单易懂，易移植
 */
public class QuickSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int start = 0, end = arr.length - 1;
        quickSort(arr, start, end);
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivotIndex = partition(arr, start, end);
        quickSort(arr, start, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, end);
    }

    private static int partition(int[] arr, int start, int end) {
        if (start == end) {
            return start;
        }
        int right = end;
        int pivotIndex = getPivotIndex(arr, start, end);
        int pivot = arr[pivotIndex];
        // 把这个 中元 换到 最右边
        swap(arr, pivotIndex, end);
        end--;
        while (start < end) {
            // 注意，这两个地方 都需要判别是否超出边界了
            while (start <= end && arr[start] < pivot) {
                start++;
            }
            while (end >= start && arr[end] >= pivot) {
                end--;
            }
            if (start < end) {
                swap(arr, start, end);
            }
        }

        // 现在 start 指针所在的位置，应该是 pivot 应该待的位置，交换一下
        swap(arr, start, right);

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
     * 三元取中值 !! 别写错了
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
        int[] arr = new int[]{4, 981, 10, -17, 0, -20, 29, 50, 8, 43, -5};
        QuickSort.sort(arr);
        System.out.println(Arrays.toString(arr));

        Random r = new Random();
        for (int i = 0; i < 30; i++) {
            int n = r.nextInt(23);
            int range = r.nextInt(79);
            if (n > 0 && range > 0) {
                int[] arr1 = ArrayUtil.generateArray(n, range);
                System.out.println("origin: " + Arrays.toString(arr1));
                int[] arr1Copy = Arrays.copyOf(arr1, arr1.length);

                QuickSort.sort(arr1);
                System.out.println("sorted: " + Arrays.toString(arr1));
                Arrays.sort(arr1Copy);
                Assert.assertArrayEquals(arr1, arr1Copy);
            }
        }

    }

}
