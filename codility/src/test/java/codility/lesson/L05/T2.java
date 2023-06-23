package codility.lesson.L05;

import org.junit.Assert;
import org.junit.Test;

/**
 CountDiv
 Compute number of integers divisible by k in range [a..b].

 https://app.codility.com/programmers/lessons/5-prefix_sums/count_div/

 这道题似乎和 前缀和 关系不大。
 直接用数学方法求解的，时间复杂度 O(1)
 */
public class T2 {

    public int solution(int A, int B, int K) {
        int result = (B / K) - (A / K);
        if (A % K == 0) {
            result++;
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, solution(6, 11, 2));
    }

}
