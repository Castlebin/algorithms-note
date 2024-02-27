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
        Arrays.fill(dp, Integer.MIN_VALUE); // 先都初始化为一个最小值，一定不会是
        dp[0] = A[0];

        for (int index = 0; index < N; index++) {
            for (int num = 1; num <= 6 && index + num < N; num++) {
                // 下一个到达的位置
                int nextIndex = index + num;
                // 下一个到达的位置的满足题意的最大值
                dp[nextIndex] = Math.max(dp[nextIndex], dp[index] + A[nextIndex]);
            }
        }

        return dp[N - 1];
    }

    @Test
    public void test() {
        Assert.assertEquals(8, solution(new int[] {1, -2, 0, 9, -1, -2}));
    }

}
