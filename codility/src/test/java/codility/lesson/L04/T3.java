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
        for (int a : A) {
            if (a >= 1 && a <= N) {
                int index = a - 1; // 计算下标
                // 巧妙的在下一次才更新，而不是每次 max 操作的时候，都要更新所有的值
                result[index] = Math.max(result[index], lastMax);

                result[index]++; // 正常的 +1 操作

                max = Math.max(max, result[index]);
            } else if (a == N + 1) {
                // 只是记下来最大值，下一次才更新
                lastMax = max;
            }
        }

        // 最后一次的 check， 因为上面是延迟更新（下一次才更新），所以，需要检查，哪些值还没有执行 max 操作
        for (int i = 0; i < N; i++) {
            result[i] = Math.max(result[i], lastMax);
        }
        return result;
    }

    /**
     * 时间复杂度为 O(N * M)。   性能不行，会超时
     */
    public int[] solution_0(int N, int[] A) {
        int[] result = new int[N];
        int max = 0;
        for (int a : A) {
            if (a >= 1 && a <= N) {
                int index = a - 1;
                result[index]++;
                max = Math.max(max, result[index]);
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
