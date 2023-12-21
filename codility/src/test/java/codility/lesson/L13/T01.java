package codility.lesson.L13;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 FibFrog

 Count the minimum number of jumps required for a frog to get to the other side of a river.
 */
public class T01 {

    public int solution(int[] A) {
        int N = A.length;
        // 从 -1 到 N，总共跳的距离是 N+1  ( A的最大 index 是 N-1 )
        // 从 i 到 j，总共跳的距离是 j-i
        if (N <= 2) {
            return 1;
        }

        int[] fibs = new int[N + 2];
        // 正常的斐波那契数列从 0, 1, 1, 2 开始，但跳 0 步没有意义，两个 1 也合并成一个，所以这里从 1, 2 开始
        fibs[0] = 1;
        fibs[1] = 2;
        for (int fibIndex = 2; fibIndex < fibs.length; fibIndex++) {
            fibs[fibIndex] = fibs[fibIndex - 1] + fibs[fibIndex - 2];
            if (fibs[fibIndex] == N + 1) {
                return 1; // 一步就能跳到终点
            } else if (fibs[fibIndex] > N + 1) {
                break; // 超过了终点，不需要再计算了
            }
        }
        // 保存跳到每个位置的最小步数
        int[] minSteps = new int[N + 1];
        Arrays.fill(minSteps, -1); // 初始时 -1 表示不可达
        for (int fibIndex = 0; fibIndex < fibs.length && fibs[fibIndex] - 1 < N; fibIndex++) {
            // 从 -1 开始跳到 fibs[index] - 1 位置，只需要一步 （跳的距离为 fibs[fibIndex]）
            int index = fibs[fibIndex] - 1;
            if (A[index] == 1) {
                minSteps[index] = 1; // 一步就能跳到的位置
            }
        }
        for (int index = 0; index < N; index++) {
            if (A[index] == 0) {
                continue; // 不可达的位置
            }
            for (int fibIndex = 0; fibIndex < fibs.length && index + fibs[fibIndex] <= N; fibIndex++) {
                // 从 index 跳到 index + fibs[fibIndex] 位置，需要一步 （跳的距离为 fibs[fibIndex]）
                int nextIndex = index + fibs[fibIndex];
                if (nextIndex < N && A[nextIndex] == 0) {
                    continue; // 不可达的位置
                }
                if (minSteps[index] == -1) {
                    continue; // 说明 index 位置不可达，所以也没法从它跳到 nextIndex
                }

                if (minSteps[nextIndex] == -1) {// 还没有计算过
                    minSteps[nextIndex] = minSteps[index] + 1;
                } else {
                    // 说明已经计算过了，取最小值
                    minSteps[nextIndex] = Math.min(minSteps[nextIndex], minSteps[index] + 1);
                }
            }
        }

        return minSteps[N];
    }

    @Test
    public void test() {
        Assert.assertEquals(3, solution(new int[] {0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0}));
    }

}
