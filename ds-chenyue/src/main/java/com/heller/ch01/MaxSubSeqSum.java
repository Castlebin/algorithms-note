package com.heller.ch01;

import java.util.Random;

public class MaxSubSeqSum {

    /**
     * 三层循环，算法复杂度O(n^3)
     */
    public static int maxSubSeqSum1(int[] a) {
        int maxSubSeqSum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int subSeqSum = 0;
                for (int t = i; t <= j; t++) {
                    subSeqSum += a[t];
                }
                if (subSeqSum > maxSubSeqSum) {
                    maxSubSeqSum = subSeqSum;
                }
            }
        }

        return maxSubSeqSum;
    }

    /**
     * 两层循环，O(n^2)
     */
    public static int maxSubSeqSum2(int[] a) {
        int maxSubSeqSum = 0;
        for (int i = 0; i < a.length; i++) {
            int lastSum = 0;
            for (int j = i; j < a.length; j++) {
                lastSum += a[j];
                if (lastSum > maxSubSeqSum) {
                    maxSubSeqSum = lastSum;
                }
            }
        }

        return maxSubSeqSum;
    }

    public static int maxSubSeqSum3(int[] a) {
        return maxSubSum3(a, 0, a.length - 1);
    }

    /**
     * 分而治之，先求出左边的最大子序列和，再求出右边的最大子序列和，
     * 在求出一个跨越中间边界的最大子序列和（等于 从中间开始朝左边相加的最大和 + 从中间开始往右边相加最大和）（跨越边界）
     * <p>
     * 最大子序列和 必然为三者中的最大者
     *
     * O( n * log(n) )
     */
    private static int maxSubSum3(int a[], int left, int right) {
        if (left == right) {
            if (a[left] > 0) {
                return a[left];
            } else {
                return 0;
            }
        }

        int middle = (left + right) / 2;
        int leftMax = maxSubSum3(a, left, middle);
        int rightMax = maxSubSum3(a, middle + 1, right);

        int maxLeftBoardSum = 0;
        int leftBoardSum = 0;
        for (int i = middle; i >= left; i--) {
            leftBoardSum += a[i];
            if (leftBoardSum > maxLeftBoardSum) {
                maxLeftBoardSum = leftBoardSum;
            }
        }

        int maxRightBoardSum = 0;
        int rightBoardSum = 0;
        for (int i = middle + 1; i <= right; i++) {
            rightBoardSum += a[i];
            if (rightBoardSum > maxRightBoardSum) {
                maxRightBoardSum = rightBoardSum;
            }
        }

        return max(leftMax, rightMax, maxLeftBoardSum + maxRightBoardSum);
    }

    public static int max(int... a) {
        int max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    /**
     * 在线算法，O(n)
     */
    public static int maxSubSeqSum4(int[] a) {
        int maxSubSeqSum = 0;
        int subSum = 0;
        for (int i = 0; i < a.length; i++) {
            subSum = subSum + a[i];
            if (subSum <= 0) {
                subSum = 0;
                continue;
            }
            if (subSum > maxSubSeqSum) {
                maxSubSeqSum = subSum;
            }
        }
        return maxSubSeqSum;
    }

    /**
     * 生成 (-10, 10) 之间的随机整数序列
     */
    public static int[] buildSeq(int n) {
        int range = 10;
        Random random = new Random();
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = range - random.nextInt(range * 2);
        }
        return a;
    }

}
