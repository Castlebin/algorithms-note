package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 43. 1～n整数中1出现的次数
 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。

 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。

 示例 1：
 输入：n = 12
 输出：5

 示例 2：
 输入：n = 13
 输出：6


 限制：
 1 <= n < 2^31
 */
public class S041 {

    public int countDigitOne(int n) {
        if (n <= 0) {
            return 0;
        }
        return countDigitOne(String.valueOf(n), 0);
    }

    public int countDigitOne(String numStr, int start) {
        if (start >= numStr.length()) {
            return 0;
        }
        int fistDigit = numStr.charAt(start) - '0';
        int len = numStr.length() - start;
        // 只剩下一个字符
        if (len == 1) {
            if (fistDigit == 0) {
                return 0;
            } else if (fistDigit > 0) {
                return 1;
            }
        }

        // 至少还剩下两个字符
        int count = 0;
        if (fistDigit > 1) {
            count += Math.pow(10, len - 1);
        } else if (fistDigit == 1){
            count += Integer.parseInt(numStr.substring(start + 1)) + 1;
        }

        count += fistDigit * (len - 1) * Math.pow(10, len - 2);
        count += countDigitOne(numStr, start + 1);

        return count;
    }

    @Test
    public void test() {
        Assert.assertEquals(12, countDigitOne(20));
        Assert.assertEquals(2, countDigitOne(10));
        Assert.assertEquals(5, countDigitOne(12));
        Assert.assertEquals(6, countDigitOne(13));
    }

}
