package codility.lesson.L06;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 Triangle

 Determine whether a triangle can be built from a given set of edges.

 https://app.codility.com/programmers/lessons/6-sorting/triangle/
 */
public class T3 {

    /**
     * 平均时间复杂度：O(N * log(N))
     */
    public int solution(int[] A) {
        // 先排序
        Arrays.sort(A);
        for (int i = 0; i <= A.length - 3; i++) {
            // 两短边大于第三边，则可以组成一个三角形
            if (A[i] > A[i + 2] - A[i + 1]) {// 防止溢出
                return 1;
            }
        }
        return 0;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, solution(new int[] {10, 2, 5, 1, 8, 20}));
        Assert.assertEquals(0, solution(new int[] {10, 50, 5, 1}));
    }

}
