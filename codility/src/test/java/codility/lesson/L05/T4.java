package codility.lesson.L05;

import org.junit.Assert;
import org.junit.Test;

/**
 MinAvgTwoSlice
 Find the minimal average of any slice containing at least two elements.

 https://app.codility.com/programmers/lessons/5-prefix_sums/min_avg_two_slice/

 这题跟 前缀和 可以说也没啥关系

 这段代码之所以正确，是因为它使用了一种有效的算法来找到具有最小平均值的切片的起始位置。该算法基于以下两个事实：

 1. 对于任何长度大于 2 的切片，它都可以分解为长度为 2 或 3 的切片。
 2. 对于任何长度为 N 的数组，具有最小平均值的切片长度必须是 2 或 3。

 因此，该算法通过迭代计算所有可能的长度为 2 和 3 的切片的平均值，并记录最小平均值及其对应的起始位置。
 最后，该算法返回具有最小平均值的切片的起始位置。由于该算法计算所有可能的切片，因此它具有正确性。
 同时，由于该算法仅计算长度为2和3的切片，因此它具有高效性。

 为什么说 对于任何长度为 N 的数组，具有最小平均值的切片长度必须是 2 或 3？
 对于任何长度为N的数组，如果有一个长度为 4 或更长的切片的平均值小于一个长度为 2 或 3 的切片的平均值，
 那么这个长切片可以分解为多个长度为 2 或 3 的切片，其中至少有一个切片的平均值小于原始的短切片。
 这意味着，如果存在一个长度为 4 或更长的切片的平均值小于一个长度为 2 或 3 的切片的平均值，那么这个长切片不是具有最小平均值的切片。

 因此，对于任何长度为 N 的数组，具有最小平均值的切片必须是长度为 2 或 3 的切片。
 */
public class T4 {

    public int solution(int[] A) {
        if (A.length == 2) {
            return 0;
        }
        double minAvg = (A[0] + A[1]) / 2.0;
        int index = 0;
        for (int i = 1; i < A.length - 2; i++) {
            long sum = (long) (A[i]) + A[i + 1];
            if (sum / 2.0 < minAvg) {
                minAvg = sum / 2.0;
                index = i;
            }
            sum += A[i + 2];
            if (sum / 3.0 < minAvg) {
                minAvg = sum / 3.0;
                index = i;
            }
        }
        if ((A[A.length - 2] + A[A.length - 1]) / 2.0 < minAvg) {
            index = A.length - 2;
        }
        return index;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, solution(new int[] {4, 2, 2, 5, 1, 5, 8}));
    }

}
