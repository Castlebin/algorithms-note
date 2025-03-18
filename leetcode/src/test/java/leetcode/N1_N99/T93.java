package leetcode.N1_N99;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 93. 复原 IP 地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * https://leetcode.cn/problems/restore-ip-addresses/
 *
 **/
public class T93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }
        backtrace(s, 0, new LinkedList<>(), result);
        return result;
    }

    /**
     * 1. 递归的终止条件是，start == s.length() && trace.size() == 4
     * 2. 如果 start >= s.length() || trace.size() >= 4，直接返回
     * 3. 否则，遍历 len = 1, 2, 3
     * 4. 如果 start + len > s.length()，直接 break
     * 5. 如果 str 的长度大于 1，并且 str.charAt(0) == '0'，直接 break
     * 6. 如果 num > 255，直接 break
     */
    void backtrace(String s, int start, LinkedList<String> trace, List<String> result) {
        // 找到一个符合条件的解
        if (start == s.length() && trace.size() == 4) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < trace.size(); i++) {
                sb.append(trace.get(i));
                if (i != trace.size() - 1) {
                    sb.append(".");
                }
            }
            result.add(sb.toString());
            return;
        }

        // 不用继续了
        if (start >= s.length() || trace.size() >= 4) {
            return;
        }

        // 依次遍历 len = 1, 2, 3 （ip的每个段最多长为 3）
        for (int len = 1; len <= 3; len++) {
            if (start + len > s.length()) { // 越界
                break;
            }

            char c = s.charAt(start + len - 1); // 不是数字
            if (c < '0' || c > '9') {
                break;
            }

            String str = s.substring(start, start + len);
            if (str.length() > 1 && str.charAt(0) == '0') { // 前导 0
                break;
            }

            int num = Integer.parseInt(str); // 大于 255
            if (num > 255) {
                break;
            }

            trace.add(str);
            backtrace(s, start + len, trace, result);
            trace.removeLast();
        }
    }

    @Test
    public void test() {
        String[] arr = new String[] {
                "0000",
                "1111",
                "101023",
                "25525511135",
        };
        for (String s : arr) {
            List<String> res = restoreIpAddresses(s);
            System.out.println("s: " + s);
            for (String str : res) {
                System.out.println(str);
            }
            System.out.println("====");
        }
    }
}
