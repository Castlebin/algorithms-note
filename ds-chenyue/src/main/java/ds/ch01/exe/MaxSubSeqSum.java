package ds.ch01.exe;

import java.util.Scanner;

/**
 * 最大子序列和问题，4种算法
 */
public class MaxSubSeqSum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(maxSubSeqSum_1(nums));
    }

    /**
     * 最笨的，3层循环，遍历所有组合序列
     * (注意，i可以等于j，表示只有一个元素的序列)
     * <p>
     * 时间复杂度 O(N^3)
     */
    public static int maxSubSeqSum_1(int[] array) {
        int maxSubSeqSum = 0;
        // 前两层循环找出子序列的起止位置，最里层循环对该序列求和(注意，i可以等于j，表示只有一个元素的序列)
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                int subSeqSum = 0;
                for (int k = i; k <= j; k++) {
                    subSeqSum += array[k];
                }
                if (subSeqSum > maxSubSeqSum) {
                    maxSubSeqSum = subSeqSum;
                }
            }
        }
        return maxSubSeqSum;
    }

    /**
     * 优化上面的三层循环，不用每次都重置序列和为0
     * <p>
     * 时间复杂度 O(N^2)
     */
    public static int maxSubSeqSum_2(int[] array) {
        int maxSubSeqSum = 0;
        for (int i = 0; i < array.length; i++) {
            int subSeqSum = 0;
            for (int j = i; j < array.length; j++) {
                subSeqSum += array[j];
                if (subSeqSum > maxSubSeqSum) {
                    maxSubSeqSum = subSeqSum;
                }
            }
        }
        return maxSubSeqSum;
    }

    /**
     * 分而治之，先求出左边的最大子序列和，再求出右边的最大子序列和，
     * 再求出一个跨越中间边界的最大子序列和（等于 从中间开始朝左边相加的最大和 + 从中间开始往右边相加最大和）（跨越边界）
     * <p>
     * 最大子序列和 必然为三者中的最大者
     * <p>
     * O( n * log(n) )
     */
    public static int maxSubSeqSum_3(int[] array) {
        return maxSubSeqSum_3(array, 0, array.length - 1);
    }

    private static int maxSubSeqSum_3(int[] array, int begin, int end) {
        if (array.length == 0) {
            return 0;
        }
        if (begin == end) {
            if (array[begin] > 0) {
                return array[begin];
            }
            return 0;
        }
        int middle = (begin + end) / 2;
        int leftMaxSubSeqSum = maxSubSeqSum_3(array, begin, middle);
        int rightMaxSubSeqSum = maxSubSeqSum_3(array, middle + 1, end);

        /**
         * 从中间从左边相加 的 最大和
         */
        int leftMaxSum = 0, leftSum = 0;
        for (int i = middle; i >= begin; i--) {
            leftSum += array[i];
            if (leftSum > leftMaxSum) {
                leftMaxSum = leftSum;
            }
        }

        /**
         * 从中间往右边相加 的 最大和
         */
        int rightMaxSum = 0, rightSum = 0;
        for (int i = middle + 1; i <= end; i++) {
            rightSum += array[i];
            if (rightSum > rightMaxSum) {
                rightMaxSum = rightSum;
            }
        }

        return max(leftMaxSubSeqSum, rightMaxSubSeqSum, leftMaxSum + rightMaxSum);
    }

    private static int max(int... nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }

    /**
     * 在线算法 O(N)，最佳
     */
    public static int maxSubSeqSum_4(int[] array) {
        int maxSubSeqSum = 0;
        int subSeqSum = 0;
        for (int i = 0; i < array.length; i++) {
            subSeqSum += array[i];
            if (subSeqSum > maxSubSeqSum) {
                maxSubSeqSum = subSeqSum;
            } else if (subSeqSum < 0) {
                subSeqSum = 0;
            }
        }
        return maxSubSeqSum;
    }

}
