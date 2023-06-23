package codility.lesson.L03;

import org.junit.Assert;
import org.junit.Test;

/**
 TapeEquilibrium

 Minimize the value |(A[0] + ... + A[P-1]) - (A[P] + ... + A[N-1]).

 https://app.codility.com/programmers/lessons/3-time_complexity/tape_equilibrium/
 */
public class T3 {

    /**
     * 时间复杂度 O(N)，空间复杂度 O(N)
     *
     * 前缀和 思想
     */
    public int solution(int[] A) {
        long[] prefixSum = new long[A.length];
        prefixSum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i];
        }
        int minTapEqu = Integer.MAX_VALUE;
        for (int i = 1; i < A.length; i++) {
            minTapEqu = (int) Math.min(minTapEqu, Math.abs(prefixSum[A.length - 1] - 2 * prefixSum[i - 1]));
        }
        return minTapEqu;
    }

    @Test
    public void test() {
        Assert.assertEquals(0, solution(new int[] {1, 1}));
        Assert.assertEquals(1, solution(new int[] {3, 1, 2, 4, 3}));
    }

}
