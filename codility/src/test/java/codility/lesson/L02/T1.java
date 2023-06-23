package codility.lesson.L02;

import org.junit.Assert;
import org.junit.Test;

/**
 CyclicRotation

 Rotate an array to the right by a given number of steps.

 https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
 */
public class T1 {

    public int[] solution(int[] A, int K) {
        if (A == null || A.length == 0 || (K % A.length == 0)) {
            return A;
        }
        int f = A.length - (K % A.length);
        rotate(A, 0, f - 1);
        rotate(A, f, A.length - 1);
        rotate(A, 0, A.length - 1);
        return A;
    }

    private void rotate(int[] A, int start, int end) {
        for (int i = start; start < end; start++, end--) {
            int tmp = A[start];
            A[start] = A[end];
            A[end] = tmp;
        }
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] {9, 7, 6, 3, 8}, solution(new int[] {3, 8, 9, 7, 6}, 3));
        Assert.assertArrayEquals(new int[] {0, 0, 0}, solution(new int[] {0, 0, 0}, 1));
        Assert.assertArrayEquals(new int[] {1, 2, 3, 4}, solution(new int[] {1, 2, 3, 4}, 4));
        Assert.assertArrayEquals(new int[] {}, solution(new int[] {}, 3));
        Assert.assertArrayEquals(null, solution(null, 3));
    }

}
