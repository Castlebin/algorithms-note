package leetcode.N1_N99;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 22. 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 */
public class T22 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrace(n, n, n, new char[2 * n], res);
        return res;
    }

    /**
     * @param n 括号对数
     * @param leftRemain 剩余的左括号的数量
     * @param rightRemain 剩余的右括号的数量
     * @param trace 记录下来的路径
     * @param res 结果集
     */
    void backtrace(int n, int leftRemain, int rightRemain, char[] trace, List<String> res) {
        // 任何时候，用掉的左括号数量必须大于等于右括号的数量
        // 所以，如果发现剩余的左括号数量比右括号多，说明不合法
        if (leftRemain > rightRemain) {
            return;
        }
        // 剩余的左括号或者右括号小于 0 ，不合法
        if (leftRemain < 0 || rightRemain < 0) {
            return;
        }
        if (leftRemain == 0 && rightRemain == 0) {
            // 很好，找到一个合法的
            res.add(new String(trace));
            return;
        }

        // 现在是为第 2 * n - leftRemain - rightRmain 个位置做选择
        int pos = 2 * n - leftRemain - rightRemain;
        // 选择左括号
        trace[pos] = '(';
        backtrace(n, leftRemain - 1, rightRemain, trace, res);
        // 回撤选择 （不用显式操作，因为有 pos 和 leftRemain 、 rightRemain 在控制状态）

        trace[pos] = ')';
        backtrace(n, leftRemain, rightRemain - 1, trace, res);
        // 回撤选择 （不用显式操作，因为有 pos 和 leftRemain 、 rightRemain 在控制状态）
    }

    @Test
    public void test() {
        System.out.println(generateParenthesis(1));
        System.out.println(generateParenthesis(3));
    }

}
