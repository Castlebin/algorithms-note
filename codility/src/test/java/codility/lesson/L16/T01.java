package codility.lesson.L16;

import org.junit.Assert;
import org.junit.Test;

/**
 MaxNonoverlappingSegments

 Find a maximal set of non-overlapping segments.
 */
public class T01 {

    public int solution(int[] A, int[] B) {
        // 获取数组长度
        int N = A.length;
        // 如果数组为空，则返回0
        if (N == 0) {
            return 0;
        }

        // 初始化非重叠片段计数为1，初始化前一个片段的结束位置为第一个片段的结束位置
        int nonOverlappingCount = 1;
        int prevEnd = B[0];

        // 遍历数组中的片段
        for (int i = 1; i < N; i++) {
            // 如果当前片段的起始位置大于前一个片段的结束位置
            if (A[i] > prevEnd) {
                // 将非重叠片段计数加1，更新前一个片段的结束位置为当前片段的结束位置
                nonOverlappingCount++;
                prevEnd = B[i];
            }
        }

        // 返回非重叠片段的计数
        return nonOverlappingCount;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, solution(new int[] {1, 3, 7, 9, 9}, new int[] {5, 6, 8, 9, 10}));
    }

}
