package sword;

import org.junit.Test;

/**
 面试题17. 打印从1到最大的n位数
 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
 比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 */
public class S017 {
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

    }

}
