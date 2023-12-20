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
     *
     * TODO 理解该算法
     */
    public int solution_3(int[] A) {
        int n = A.length;
        int[] sum = new int[n];

        for (int i = 0; i < n; i++) {
            int right;
            //if i+A[i]<= n-1, that's it, extract this i+A[i], let sum[i+A[i]]++, means there is one disk that i+A[i]
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
                ans -= sum[left - 1];//Find the number that is smaller than 1-A[i], sum[n-1] will never be used as we only need sum[n-1-1] at most.
            }
        }

        if (ans > 10000000) {
            return -1;
        }

        return (int) ans;
    }


    public int solution_2(int[] A) {
        int MAX = 10_000_000;

        int N = A.length;
        long[] startPoints = new long[N];
        long[] endPoints = new long[N];

        for (int i = 0; i < N; i++) {
            startPoints[i] = (long) i - A[i];
            endPoints[i] = (long) i + A[i];
        }

        Arrays.sort(startPoints);
        Arrays.sort(endPoints);

        int intersectCount = 0;
        int activeDiscs = 0;

        for (int startPoinIndex = 0, endPointIndex = 0; startPoinIndex < N; startPoinIndex++) {
            while (endPointIndex < N && endPoints[endPointIndex] < startPoints[startPoinIndex]) {
                activeDiscs--;
                endPointIndex++;
            }

            intersectCount += activeDiscs;
            activeDiscs++;

            if (intersectCount > MAX) {
                return -1;
            }
        }

        return intersectCount;
    }

    /**
     使用了排序，平均时间复杂度 O(N * log(N))

     TODO 重点理解该算法
     */
    public int solution(int[] A) {
        int MAX = 10_000_000;

        long[] startPoints = new long[A.length];
        long[] endPoints = new long[A.length];
        for (int i = 0; i < A.length; i++) {
            startPoints[i] = (long) i - A[i]; // 防止溢出
            endPoints[i] = (long) i + A[i]; // 防止溢出
        }

        // 将 startPoints 和 endPoints 分别排序
        Arrays.sort(startPoints);
        Arrays.sort(endPoints);

        int intersectCount = 0;
        for (int endPointIndex = 0, startPointIndex = 1; endPointIndex < A.length; endPointIndex++) {
            // 画图可知，如果 endPoints[endPointIndex] >= startPoints[startPointIndex]，则说明有交集
            while (startPointIndex < A.length && endPoints[endPointIndex] >= startPoints[startPointIndex]) {
                intersectCount += startPointIndex - endPointIndex;
                startPointIndex++;

                if (intersectCount > MAX) {
                    return -1;
                }
            }
        }
        return intersectCount;
    }

    /**
     直接的暴力算法，时间复杂度 O(N * N)

     两个圆相交的充要条件是：两圆心的距离小于等于两圆半径之和
     */
    public int solution_1(int[] A) {
        int MAX = 10_000_000;

        int N = A.length;
        int intersectCount = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // 两圆心的距离
                long distance = j - i;
                // 两圆半径之和
                long sumOfRadius = (long) A[i] + A[j];
                // 如果两圆心的距离小于等于两圆半径之和，则两圆相交
                if (distance <= sumOfRadius) {
                    intersectCount++;
                    if (intersectCount > MAX) {
                        return -1;
                    }
                }
            }
        }

        return intersectCount;
    }


    @Test
    public void test() {
        Assert.assertEquals(11, solution(new int[] {1, 5, 2, 1, 4, 0}));
        Assert.assertEquals(2, solution(new int[] {1, 2147483647, 0}));// 测试计算溢出
    }

}
