package codility.lesson.L05;

import org.junit.Assert;
import org.junit.Test;

/**
 CountDiv
 Compute number of integers divisible by k in range [a..b].

 https://app.codility.com/programmers/lessons/5-prefix_sums/count_div/

 可以看做是一种前缀和思想的使用。 X / K  代表的是 [0, X] 中 K 的倍数的个数。
 所以 [A, B] 中 K 的倍数的个数就是 [0, B] 中 K 的倍数的个数减去 [0, A - 1] 中 K 的倍数的个数。
 再考虑到 A 可能是 K 的倍数，此时还要加 1。
 */
public class T2 {

    public int solution(int A, int B, int K) {
        int result = (B / K) - (A / K);
        if (A % K == 0) {
            result += 1;
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, solution(6, 11, 2));
    }

}
