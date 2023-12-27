package codility.lesson.L15;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 CountDistinctSlices

 Count the number of distinct slices (containing only unique numbers).
 */
public class T02 {

    /**
     时间复杂度 O(N) ，遍历一次数组即可
        空间复杂度 O(M)。申请了一个数组，用于存储每个元素的最后出现位置
     */
    public int solution(int M, int[] A) {
        int MAX = 1_000_000_000;
        int N = A.length;

        // 用于存储每个元素的最后出现位置的数组
        int[] lastOccurrence = new int[M + 1];
        // 使用-1初始化最后出现位置数组
        Arrays.fill(lastOccurrence, -1);

        int distinctSlices = 0;
        int start = 0;
        for (int end = 0; end < N; end++) {
            // 如果位置 'end' 处的元素之前在当前切片中出现过，
            // 更新起始位置为重复元素的下一个位置
            int v = A[end];
            start = Math.max(start, lastOccurrence[v] + 1);

            // 更新当前元素的最后出现位置
            lastOccurrence[v] = end;

            // 计算以位置 'end' 结束的不同切片数量
            distinctSlices += (end - start + 1);

            // 如果不同切片数量超过了限制，返回限制值
            if (distinctSlices > MAX) {
                return MAX;
            }
        }

        return distinctSlices;
    }

    @Test
    public void test() {
        Assert.assertEquals(9, solution(6, new int[] {3,4, 5, 5, 2}));
    }

}
