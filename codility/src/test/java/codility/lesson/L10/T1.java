package codility.lesson.L10;

import org.junit.Assert;
import org.junit.Test;

/**
 CountFactors

 Count factors of given number n.

 https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/count_factors/
 */
public class T1 {

    public int solution(int N) {
        int count = 0;
        for (int i = 1; i <= Math.sqrt(N); i++) {
            if (N % i == 0) {
                count++;
                if (N / i != i) {
                    count++;
                }
            }
        }
        return count;
    }

    @Test
    public void test() {
        Assert.assertEquals(8, solution(24));
        Assert.assertEquals(1, solution(1));
        Assert.assertEquals(60, solution(5040));
        Assert.assertEquals(2, solution(780291637)); // 质数
    }

}
