package codility.lesson.L02;

import org.junit.Assert;
import org.junit.Test;

/**
 OddOccurrencesInArray

 Find value that occurs in odd number of elements.

 https://app.codility.com/programmers/lessons/2-arrays/odd_occurrences_in_array/
 */
public class T2 {

    public int solution(int[] A) {
        int result = A[0];
        for (int i = 1; i < A.length; i++) {
            result ^= A[i];
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(7, solution(new int[] {9, 3, 9, 3, 9, 7, 9}));
    }

}
