package sword.sort;

import common.ArrayUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * 注意 边界判断！！ 边界条件太多！稍不留神就会出错
 *
 * 简单易懂，易移植
 */
public class QuickSort {

    public static void sort(int[] arr) {
        // 边界判断
        if (arr == null || arr.length <= 1) {
            return;
        }

        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int start, int end) {
        // 边界判断
        if (start >= end) {
            return;
        }

        int pivotIndex = partition(arr, start, end);
        // 边界判断
        if (pivotIndex > start) {
            quickSort(arr, start, pivotIndex - 1);
        }
        if (pivotIndex < end) {
            quickSort(arr, pivotIndex + 1, end);
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
        int[] arr = new int[]{4, 981, 10, -17, 0, -20, 29, 50, 8, 43, -5};
        QuickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{3, 3, 4};
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
