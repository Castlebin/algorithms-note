package codility.lesson.L17;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 NumberSolitaire

 In a given array, find the subset of maximal sum in which the distance between consecutive elements is at most 6.
 */
public class T01 {

    public int solution(int[] A) {
        int N = A.length;
        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = A[0];

        for (int index = 0; index < N; index++) {
            for (int num = 1; num <= 6 && index + num < N; num++) {
                int nextIndex = index + num;
                if (dp[nextIndex] == Integer.MIN_VALUE) {// 还没有到达过
                    dp[nextIndex] = dp[index] + A[nextIndex];
                } else { // 曾经到达过
                    dp[nextIndex] = Math.max(dp[nextIndex], dp[index] + A[nextIndex]);
                }
            }
        }

        return dp[N - 1];
    }

    @Test
    public void test() {
        Assert.assertEquals(8, solution(new int[] {1, -2, 0, 9, -1, -2}));
    }

}
