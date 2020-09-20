package sword;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 面试题17. 打印从1到最大的n位数
 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
 比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

 使用排列解决

 leetcode 实际简化了这道题 （int），没有考虑大数问题
 */
public class S017 {

    /**
     * 考虑到大数问题，因此用字符串表示数字
     * 使用全排列解决
     *
     * 顺序生成 [0-n] 之间的数 （要 1-n 的话，去掉开头的 0 即可）
     *
     * (其实也不严禁，因为现阶段 所有数组、集合 的size ，限定在 int 范围内了)
     */
    public List<String> getNumbersBig(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must > 0");
        }

        List<String> result = new LinkedList<>();
        for (int digit = 0; digit <= 9; digit++) {
            result.add(String.valueOf(digit));
        }
        if (n == 1) {
            return result;
        }

        for (int c = 2, start = 1; c <= n; c++) {
            int lastSize = result.size();
            for (int i = start; i < lastSize; i++) {
                String num = result.get(i);
                for (int digit = 0; digit <= 9; digit++) {
                    result.add(num + digit);
                }
            }
            start = lastSize;
        }
        return result;
    }

    /**
     * 不考虑大数问题，直接就行
     */
    public int[] printNumbers(int n) {
        if (n <= 0) {
            return new int[]{};
        }
        int numbers = (int)Math.pow(10, n);
        int[] result = new int[numbers - 1];
        for (int i = 1; i < numbers; i++) {
            result[i - 1] = i;
        }
        return result;
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(printNumbers(3)));
        System.out.println(getNumbersBig(1));
        System.out.println(getNumbersBig(2));
        System.out.println(getNumbersBig(3));
        System.out.println(getNumbersBig(4));
        System.out.println(getNumbersBig(5));
    }

}
