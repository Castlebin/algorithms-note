package algorithm;

import org.junit.Test;

/**
 实现atoi这个函数，将一个字符串转换为整数。如果没有合法的整数，返回0

 样例：
     "10" =>10
     "-1" => -1
     "123123123123123" => 2147483647
     "-354353453453453 " => -2147483648
     "1.0" => 1
     "123343adf12" => 0

 */
public class Atoi {

    public int atoi(String str) {
        // 注意空格处理
        if (str == null || (str = str.trim()).length() == 0) {
            return 0;
        }
        boolean neg = false;
        if (str.startsWith("-")) {
            neg = true;
            str = str.substring(1);
        }
        // + 号也要处理
        if (str.startsWith("+")) {
            str = str.substring(1);
        }
        // 小数
        if (str.contains(".")) {
            String[] parts = str.split("\\.");
            if (parts.length > 2) {
                // 说明不是合法的小数
                return 0;
            }
            // 判断第二部分是否为空或者全0
            if (!allZero(parts[1])) {
                return 0;
            }
            str = parts[0];
        }
        long res = 0;
        for (char c : str.toCharArray()) {
            if (c < '0' || c > '9') {
                return 0;
            }
            res = res * 10 + (c - '0');
            if (res - 1 > Integer.MAX_VALUE && neg) {
                return Integer.MIN_VALUE;
            }
            if (res > Integer.MAX_VALUE && !neg) {
                return Integer.MAX_VALUE;
            }
        }
        return neg? -(int)(res) : (int)res;
    }

    private boolean allZero(String part) {
        if (part.equals("")) {
            return true;
        }
        for (char c : part.toCharArray()) {
            if (c != '0') {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        System.out.println(atoi("10"));
        System.out.println(atoi("-1"));
        System.out.println(atoi("-111222"));
        System.out.println(atoi("123123123123123"));
        System.out.println(atoi("-354353453453453   "));
        System.out.println(atoi("+354353453453453   "));
        System.out.println(atoi("123343adf12"));
        System.out.println(atoi("-123343adf12"));
        System.out.println(atoi("1.0"));
    }

}
