package sword;

import org.junit.Test;

import java.util.Stack;

/**
 面试题30. 包含min函数的栈

 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数
 在该栈中，
 调用 min、push 及 pop 的时间复杂度都是 O(1)。
 */
public class S030 {

    class MinStack {

        private Stack<Integer> data = new Stack<>();
        private Stack<Integer> min = new Stack<>();

        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int x) {
            data.push(x);
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

        public int top() {
            return data.peek();
        }

        public int min() {
            return min.peek();
        }

    }

    @Test
    public void test() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.min();  // --> 返回 -3.
        minStack.pop();
        minStack.top();  // --> 返回 0.
        minStack.min();  // --> 返回 -2.
    }

}
