package codility.lesson.L13;

import org.junit.Assert;
import org.junit.Test;

/**
 Ladder

 Count the number of different ways of climbing to the top of a ladder.
 */
public class T02 {

    public int[] solution(int[] A, int[] B) {
        // 首先，求出 A 中最大的数。这个决定了最大的斐波那契数列的长度
        int max = 0;
        for (int index = 0; index < A.length; index++) {
            max = Math.max(max, A[index]);
        }
        int[] fibs = new int[max + 2];
        fibs[0] = 0;
        fibs[1] = 1;
        for (int index = 2; index < fibs.length; index++) {
            fibs[index] = (fibs[index - 1] + fibs[index - 2]) % (1 << 30); // 注意，因为结果要对 2^B[i] 取模，所以这里对 2^30 取模，用位运算。否则答案是不准确的
        }
        int[] result = new int[A.length];
        for (int index = 0; index < A.length; index++) {
            result[index] = fibs[A[index] + 1] % (1 << B[index]);
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{5, 1, 8, 0, 1}, solution(new int[]{4, 4, 5, 5, 1}, new int[]{3, 2, 4, 3, 1}));
    }

}
