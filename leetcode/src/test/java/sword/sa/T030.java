package sword.sa;

import java.util.Stack;

import org.junit.Test;

/**
 * 剑指 Offer 30. 包含min函数的栈
 *
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。、
 *
 * https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof
 */
public class T030 {

    class MinStack {

        // 正常的 stack
        private Stack<Integer> data = new Stack<>();

        // 每次都存放当前最小值的 stack，pop、push 的时候，需要维护当前最小值在栈顶
        private Stack<Integer> min = new Stack<>();

        public MinStack() {
        }

        public void push(int x) {
            data.push(x);
            // 注意，这里用的是 <=，所以，不用计数
            if (min.isEmpty() || x <= min.peek()) {
                min.push(x);
            }
        }

        public void pop() {
            int x = data.pop();
            if (x == min.peek()) {
                min.pop();
            }
        }

        public int top() {// 正常的返回栈顶元素
            return data.peek();
        }

        public int min() {// 返回当前的最小值
            return min.peek();
        }
    }

    @Test
    public void test() {

    }

}
