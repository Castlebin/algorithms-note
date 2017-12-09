package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 6. ZigZag Conversion
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed
 * font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */
public class N006 {
    public String convert(String s, int nRows) {
        if (nRows <= 0) {
            return "";
        }
        if (s == null || s.length() <= 1 || nRows == 1) {
            return s;
        }

        // 初始化StringBuffer数组
        StringBuffer[] sb = new StringBuffer[nRows];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuffer();
        }

        int t = 0;
        for (int i = 0, idx = 0; i < s.length(); i++) {
            if (t == 0) {// 从上往下走
                if (idx < 0) {
                    idx = 1;
                }
                sb[idx++].append(s.charAt(i));
                if (idx == sb.length) {
                    t = 1;
                }
            } else {// 从下往上走
                if (idx == sb.length) {
                    idx = sb.length - 2;
                }
                sb[idx--].append(s.charAt(i));
                if (idx <= 0) {
                    t = 0;
                }
            }
        }

        for (int idx = 1; idx < sb.length; idx++) {
            sb[0].append(sb[idx]);
        }
        return sb[0].toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(convert("PAYPALISHIRING", 3), "PAHNAPLSIIGYIR");
        Assert.assertEquals(convert("ABCD", 2), "ACBD");
        Assert.assertEquals(convert("ABCDEF", 4), "ABFCED");
    }
}
