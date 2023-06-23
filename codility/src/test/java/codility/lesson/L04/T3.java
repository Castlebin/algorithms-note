package codility.lesson.L04;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 MaxCounters

 Calculate the values of counters after applying all alternating operations:
 increase counter by 1; set value of all counters to current maximum.
 */
public class T3 {

    /**
     * 时间复杂度为 O(N + M)
     */
    public int[] solution(int N, int[] A) {
        int[] result = new int[N];
        int max = 0;
        int lastMax = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 1 && A[i] <= N) {
                // 巧妙的在下一次才更新，而不是每次 max 操作的时候，都要更新所有的值
                result[A[i] - 1] = Math.max(result[A[i] - 1], lastMax);
                result[A[i] - 1]++;
                max = Math.max(max, result[A[i] - 1]);
            } else if (A[i] == N + 1) {
                lastMax = max;
            }
        }
        // 因为上面是延迟更新（下一次才更新），所以，需要检查，哪些值还没有执行 max 操作
        for (int i = 0; i < N; i++) {
            result[i] = Math.max(result[i], lastMax);
        }
        return result;
    }

    /**
     * 时间复杂度为 O(N * M)
     */
    public int[] solution_0(int N, int[] A) {
        int[] result = new int[N];
        int max = 0;
        for (int a : A) {
            if (a >= 1 && a <= N) {
                result[a - 1]++;
                max = Math.max(max, result[a - 1]);
            } else if (a == N + 1) {
                // 这个操作导致时间复杂度高！！
                Arrays.fill(result, max);
            }
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] {3, 2, 2, 4, 2}, solution(5, new int[] {3, 4, 4, 6, 1, 4, 4}));
    }

}
