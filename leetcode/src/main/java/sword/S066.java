package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 66. 构建乘积数组
 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。
 ** 不能使用除法。**

 示例:

 输入: [1,2,3,4,5]
 输出: [120,60,40,30,24]

 提示：
 所有元素乘积之和不会溢出 32 位整数
 a.length <= 100000
 */
public class S066 {

    /**
     * 由于 不能使用除法，注意到 B[i] 计算的公式，前部分和后部分都是可以复用的，
     * 因此，用两个数组，计算保存下结果
     */
    public int[] constructArr(int[] a) {
        if (a == null || a.length <= 1) {
            return new int[0];
        }
        int[] ap = new int[a.length];
        ap[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            ap[i] = ap[i - 1] * a[i];
        }
        int[] bp = new int[a.length];
        bp[a.length - 1] = a[a.length - 1];
        for (int i = a.length - 2; i >= 0; i--) {
            bp[i] = bp[i + 1] * a[i];
        }

        int[] result = new int[a.length];
        result[0] = bp[1];
        result[a.length - 1] = ap[a.length - 2];
        for (int i = 1; i < a.length - 1; i++) {
            result[i] = ap[i - 1] * bp[i + 1];
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{120,60,40,30,24}, constructArr(new int[]{1,2,3,4,5}));
    }

}
