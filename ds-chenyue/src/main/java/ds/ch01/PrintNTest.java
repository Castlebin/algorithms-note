package ds.ch01;

import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;

/**
 * 打印从1 ~ n 的整数
 */
public class PrintNTest {

    /**
     * 1. 递归实现
     *
     * 打印N之前，先打印N-1 （空间占用太高、栈溢出）
     */
    public static void printN_1(int n) {
        if (n > 0) {
            printN_1(n - 1);
            System.out.println(n);
        }
    }

    /**
     * 2. 循环实现
     */
    public static void printN_2(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(i);
        }
    }

    @Test
    public void testBasic() {
        printN_1(10);
        System.out.println("-----------");
        printN_2(10);
    }

    @Test
    public void testPrintN() {
        int[] nArray = new int[]{10, 100, 1000};
        for (int n : nArray) {
            long begin = System.currentTimeMillis();
            int repeatTimes = 1000;
            for (int i = 0; i < repeatTimes; i++) {
                printN_1(n);
            }
            long t1 = (System.currentTimeMillis() - begin) / repeatTimes;

            begin = System.currentTimeMillis();
            for (int i = 0; i < repeatTimes; i++) {
                printN_2(n);
            }
            long t2 = (System.currentTimeMillis() - begin) / repeatTimes;

            System.err.println(n + " : " + t1 + "\t" + t2);
        }
    }

}
