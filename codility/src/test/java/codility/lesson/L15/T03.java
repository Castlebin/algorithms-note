package codility.lesson.L15;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 CountTriangles

 Count the number of triangles that can be built from a given set of edges.
 */
public class T03 {

    public int solution(int[] A) {
        Arrays.sort(A);
        int count = 0;
        int N = A.length;
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1, k = j + 1; j < N - 1; j++) {
                while (k < N && A[i] + A[j] > A[k]) {
                    k++;
                }
                count += k - j - 1;
            }
        }

        return count;
    }

    @Test
    public void test() {
        Assert.assertEquals(4, solution(new int[] {10, 2, 5, 1, 8, 12}));
    }

}
