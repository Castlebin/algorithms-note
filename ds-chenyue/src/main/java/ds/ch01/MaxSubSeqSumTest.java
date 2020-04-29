package ds.ch01;

import ds.ch01.exe.MaxSubSeqSum;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class MaxSubSeqSumTest {

    @Test
    public void testMaxSubSeqSum() {
        System.out.println(Arrays.toString(sequence));
        int maxSubSeqSum_1 = MaxSubSeqSum.maxSubSeqSum_1(sequence);
        int maxSubSeqSum_2 = MaxSubSeqSum.maxSubSeqSum_2(sequence);
        int maxSubSeqSum_3 = MaxSubSeqSum.maxSubSeqSum_3(sequence);
        int maxSubSeqSum_4 = MaxSubSeqSum.maxSubSeqSum_4(sequence);
        System.out.println(maxSubSeqSum_1 + ", " + maxSubSeqSum_2 + ", " + maxSubSeqSum_3 + ", " + maxSubSeqSum_4);
        Assert.assertEquals(maxSubSeqSum_1, maxSubSeqSum_2);
        Assert.assertEquals(maxSubSeqSum_2, maxSubSeqSum_3);
        Assert.assertEquals(maxSubSeqSum_3, maxSubSeqSum_4);
    }

    @Test
    public void multiTest() {
        Random r = new Random();
        for (int t = 0; t < 30; t++) {
            sequence = buildSeq(1000, r.nextInt(100));
            testMaxSubSeqSum();
        }
    }

    private static int[] sequence;

    @BeforeClass
    public static void beforeClass() {
        sequence = buildSeq(10, 8);
    }

    @Test
    public void test() {
        sequence = new int[]{};
        testMaxSubSeqSum();
    }

    @Test
    public void test1() {
        sequence = new int[]{1};
        testMaxSubSeqSum();
    }

    @Test
    public void test2() {
        sequence = new int[]{-1};
        testMaxSubSeqSum();
    }



    /**
     * 生成 (-range, range) 之间的随机整数序列，长度为n
     */
    public static int[] buildSeq(int range, int n) {
        Random random = new Random();
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = range - random.nextInt(range * 2);
        }
        return a;
    }

}
