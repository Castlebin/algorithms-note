package codility.lesson.L06;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 NumberOfDiscIntersections

 Compute the number of intersections in a sequence of discs.

 https://app.codility.com/programmers/lessons/6-sorting/number_of_disc_intersections/
 */
public class T4 {
    /**
     * 还有个时间复杂度为 O(N) 的算法，没有理解，👁
     */
    class Solution {
        public int solution(int[] A) {
            int n = A.length;
            int[] sum = new int[n];

            for (int i = 0; i < n; i++) {
                int right;
                //if i+A[i]<= n-1, that's it, extract this i+A[i], let sum[i+A[i]]++, means there is one disk that
                // i+A[i]
                if (n - i - 1 >= A[i]) {
                    right = i + A[i];
                } else {
                    right = n - 1;
                }

                sum[right]++;
            }

            for (int i = 1; i < n; i++) {
                sum[i] += sum[i - 1];  //sum[i] means that there are sum[i] number of values that <= i;
            }

            long ans = (long) n * (n - 1) / 2;

            for (int i = 0; i < n; i++) {
                int left;

                if (A[i] > i) {
                    left = 0;
                } else {
                    left = i - A[i];// Find the positive i-A[i].
                }

                if (left > 0) {
                    ans -= sum[left
                            - 1];//Find the number that is smaller than 1-A[i], sum[n-1] will never be used as we
                    // only need sum[n-1-1] at most.
                }
            }

            if (ans > 10000000) {
                return -1;
            }

            return (int) ans;
        }
    }

    /**
     * 使用了排序，平均时间复杂度 O(N * log(N)).
     * 这个算法还能理解
     */
    public int solution(int[] A) {
        long[] startPoints = new long[A.length];
        long[] endPoints = new long[A.length];
        for (int i = 0; i < A.length; i++) {
            startPoints[i] = (long) i - A[i]; // 防止溢出
            endPoints[i] = (long) i + A[i]; // 防止溢出
        }
        Arrays.sort(startPoints);
        Arrays.sort(endPoints);
        int count = 0;
        int j = 1;
        for (int i = 0; i < A.length; i++) {
            while (j < A.length && endPoints[i] >= startPoints[j]) {
                count += j - i;
                j++;
                if (count > 10_000_000) {
                    return -1;
                }
            }
        }
        return count;
    }

    /**
     * 直接的暴力算法，时间复杂度 O(N * N)
     */
    public int solution_1(int[] A) {
        final int MAX = 10_000_000;
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                long iStart = i - (long) A[i], iEnd = i + (long) A[i];
                long jStart = j - (long) A[j], jEnd = j + (long) A[j];
                // 判断两条线段有重叠部分的条件 第一条线段的结束大于等于第二条的开始，并且第二条的结束大于等于第一条的结束
                if ((iEnd >= jStart && jEnd >= iEnd) || (jEnd >= iStart && iEnd >= jEnd)) {
                    count++;
                    if (count >= MAX) {
                        return -1;
                    }
                }
            }
        }
        return count;
    }

    @Test
    public void test() {
        Assert.assertEquals(11, solution(new int[] {1, 5, 2, 1, 4, 0}));
        Assert.assertEquals(2, solution(new int[] {1, 2147483647, 0}));// 测试计算溢出
    }

}
