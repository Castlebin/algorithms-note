package codility.lesson.L15;

import org.junit.Assert;
import org.junit.Test;

/**
 CountDistinctSlices

 Count the number of distinct slices (containing only unique numbers).
 */
public class T02 {

    public int solution(int M, int[] A) {
        int MAX = 1_000_000_000;

        int N = A.length;


        return -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(9, solution(6, new int[] {3, 4, 5, 5, 2}));
    }

}
