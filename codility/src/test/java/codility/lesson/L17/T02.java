package codility.lesson.L17;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 MinAbsSum

 Given array of integers, find the lowest absolute sum of elements.
 */
public class T02 {

    /**
     * 由题可知，数字本身的符号没有意义。所以将数字全部转为为绝对值后，问题转变为：
     * 将数字分成 2 堆，P 和 Q  （ 我们假设 sum(P) <= sum(Q)  ）要求 sum(Q) - sum(P) 的值最小。求这个最小的 sum(Q) - sum(P)  。
     *
     * 假设所有数字（绝对值）的和为 S ，那么问题变为：
     * sum(P) <= S/2 <= sum(Q)  ，sum(P) 小于等于 S/2 , 越接近越好。最接近时，符合答案。
     * 此时的答案为：sum(Q) - sum(P) ，即：[S - sum(P)] - sum(P) .即  S - 2 * sum(P) .
     *
     * 所以，我们先求满足 sum(P) <= S / 2 的最大的  sum(P) 即可.
     */
    public int solution(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        // 先把所有的数字，都处理成它的绝对值，并且，找出最大的数字
        int N = A.length;
        int M = 0;
        int S = 0;
        for (int index = 0; index < N; index++) {
            A[index] = Math.abs(A[index]);
            M = Math.max(M, A[index]);
            S += A[index];
        }
        Arrays.sort(A);  // 将 A 进行排序，可以提前退出，减少计算量


        int result = S;
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(0, solution(new int[] {1, 5, 2, -2}));
        Assert.assertEquals(2, solution(new int[] {3, 1}));
        Assert.assertEquals(3, solution(new int[] {-3}));
    }

}
