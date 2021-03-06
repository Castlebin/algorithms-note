package sword;

import org.junit.Test;

import java.util.*;

/**
 剑指 Offer 40. 最小的k个数

 输入整数数组 arr ，找出其中最小的 k 个数。
 例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 */
public class S040 {

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k <= 0) {
            return new int[]{};
        }
        if (arr == null || arr.length == 0 || arr.length <= k) {
            return arr;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int item : arr) {
            if (heap.size() < k) {
                heap.add(item);
            } else if (heap.peek() > item) {
                heap.remove();
                heap.add(item);
            }
        }
        int[] result = new int[heap.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = heap.remove();
        }
        return result;
    }

    @Test
    public void test() {

    }

}
