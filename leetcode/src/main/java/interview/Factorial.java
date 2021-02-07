package interview;

import java.util.ArrayList;
import java.util.List;

/**
    求 N 的阶乘   大数问题
*/
public class Factorial {

    /**
     * 求 n 的阶乘
     */
    public String factorial(int n) {
        String result = "1";
        for (int i = 1; i <= n; i++) {
            result = mul(result, String.valueOf(i));
        }
        return result;
    }

    /**
     * 大数相乘
     */
    public String mul(String num1, String num2) {
        // 先把 num2 设置较小的那个数
        if (num2.length() > num1.length()) {
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
        }
        List<String> muls = new ArrayList<>();
        String t = "";
        for (int p2 = num2.length() - 1, index = 0; p2 >= 0; p2--, index++) {
            String n2 = mulOneNum(num1, num2.charAt(p2) - '0') + t;
            t += '0';
            muls.add(n2);
        }
        String res = "0";
        for (int i = 0; i < muls.size(); i++) {
            res = add(res, muls.get(i));
        }
        return res;
    }

    // 一个数 与 一个个位数相乘
    public String mulOneNum(String num1, int n) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int p1 = num1.length() - 1; p1 >= 0 || carry > 0; p1--) {
            int n1 = p1 >= 0 ? num1.charAt(p1) - '0' : 0;
            int mul = n1 * n + carry;
            carry = mul / 10;
            result.insert(0, (char) (mul % 10 + '0'));
        }
        return result.toString();
    }

    // 大数相加
    public String add(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int p1 = num1.length() - 1, p2 = num2.length() - 1;
             p1 >= 0 || p2 >= 0 || carry > 0; p1--, p2--) {
            int n1 = p1 >= 0 ? num1.charAt(p1) - '0' : 0;
            int n2 = p2 >= 0 ? num2.charAt(p2) - '0' : 0;
            int added = n1 + n2 + carry;
            if (added >= 10) {
                carry = 1;
                added = added - 10;
            } else {
                carry = 0;
            }
            result.insert(0, (char) (added + '0'));
        }
        return result.toString();
    }

}
