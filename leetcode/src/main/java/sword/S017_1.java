package sword;

import org.junit.Test;

import java.util.Arrays;

/**
 面试题17. 打印从1到最大的n位数
 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
 比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

 利用递归来实现，全排列，可以解决大数问题
 */
public class S017_1 {
    public void printNumbers(int n) {
        if (n <= 0) {
            return;
        }
        char[] number = new char[n];
        Arrays.fill(number, '0');
        for (char ch = '0'; ch <= '9'; ch++) {
            number[0] = ch;
            printRecursively(number, n, 0);
        }
    }

    public void printRecursively(char[] number, int length, int index) {
        if (index == length - 1) {
            String num = getNumberWithoutPrefixZero(number);
            if (!num.equals("0")) {
                System.out.print(num + ", ");
            }
            return;
        }
        for (char ch = '0'; ch <= '9'; ch++) {
            number[index + 1] = ch;
            printRecursively(number, length, index + 1);
        }
    }

    public String getNumberWithoutPrefixZero(char[] number) {
        if (number.length == 0) {
            throw new RuntimeException("代表数字的字符数组长度不能为0");
        }
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < number.length && number[index] == '0') {
            index++;
        }
        for (;index < number.length; index++) {
            sb.append(number[index]);
        }
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();
    }

    @Test
    public void test() {
        printNumbers(3);
    }

}
