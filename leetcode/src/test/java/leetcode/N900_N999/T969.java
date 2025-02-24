package leetcode.N900_N999;

import java.util.ArrayList;
import java.util.List;

/**
 * 969. 煎饼排序
 */
public class T969 {
    /**
     * 最直观的算法 （可能不是 翻转步骤最少 的解法）
     * 每次通过最多两次翻转，将当前最大值移动到正确的位置
     */
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> res = new ArrayList<>();
        // 从后往前遍历，每次操作，都是将当前 [0, index] 中的最大值移动到 index 位置
        for (int index = arr.length - 1; index >= 0; index--) {
            // 找到 [0, index] 中的最大值 的位置
            int maxIndex = findMaxIndex(arr, index);
            if (maxIndex == index) { // 已经在正确位置了，什么也不用做
                continue;
            }
            if (maxIndex != 0) { // 在位置 0 的话，这一步也不用翻转
                reverse(arr, maxIndex); // 翻转 [0, maxIndex]，将最大值移动到 0 位置 （最顶上）
                res.add(maxIndex + 1);
            }
            reverse(arr, index); // 翻转 [0, index]，将最大值移动到 index 位置 （底下）
            res.add(index + 1);
        }
        return res;
    }

    /**
     * 寻找 [0, end] 中的最大值 的位置
     */
    int findMaxIndex(int[] arr, int end) {
        int maxIndex = 0;
        for (int index = 1; index <= end; index++) {
            if (arr[index] > arr[maxIndex]) {
                maxIndex = index;
            }
        }
        return maxIndex;
    }

    /**
     * 翻转 [0, end] 位置的元素
     * */
    void reverse(int[] arr, int end) {
        int start = 0;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

}
