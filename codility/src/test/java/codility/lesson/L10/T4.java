package codility.lesson.L10;

import org.junit.Assert;
import org.junit.Test;

/**
 Peaks

 Divide an array into the maximum number of same-sized blocks, each of which should contain an index P such that A[P - 1] < A[P] > A[P + 1].

 https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/peaks/
 */
public class T4 {

    /**
     * TODO 解题
     */
    public int solution(int[] A) {
        return 0;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, solution(new int[] {1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2}));
    }

}
