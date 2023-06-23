package codility.lesson.L04;

import org.junit.Assert;
import org.junit.Test;

/**
 MissingInteger

 Find the smallest positive integer that does not occur in a given sequence.

 https://app.codility.com/programmers/lessons/4-counting_elements/missing_integer/
 */
public class T4 {

    public int solution(int[] A) {
        boolean[] flags = new boolean[A.length];
        for (int i = 0; i < A.length; i++) {
            int num = A[i];
            if (num >= 1 && num <= A.length) {
                flags[num - 1] = true;
            }
        }
        for (int i = 0; i < flags.length; i++) {
            if (!flags[i]) {
                return i + 1;
            }
        }
        return A.length + 1;
    }

    @Test
    public void test() {
        Assert.assertEquals(5, solution(new int[] {1, 3, 6, 4, 1, 2}));
        Assert.assertEquals(4, solution(new int[] {1, 2, 3}));
        Assert.assertEquals(1, solution(new int[] {-1, -3}));
    }

}
