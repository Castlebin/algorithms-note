package codility.lesson.L09;

import org.junit.Assert;
import org.junit.Test;

/**
 MaxProfit

 Given a log of stock prices compute the maximum possible earning.

 https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_profit/
 */
public class T1 {

    public int solution(int[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }
        int maxProfit = 0, curProfit = 0;
        for (int i = 1; i < A.length; i++) {
            curProfit += A[i] - A[i - 1];
            if (curProfit > maxProfit) {
                maxProfit = curProfit;
            } else if (curProfit < 0) {
                curProfit = 0;
            }
        }
        return maxProfit;
    }

    @Test
    public void test() {
        Assert.assertEquals(356, solution(new int[] {23171, 21011, 21123, 21366, 21013, 21367}));
    }

}
