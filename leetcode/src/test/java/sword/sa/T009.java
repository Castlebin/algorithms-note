package sword.sa;

import java.util.Stack;

import org.junit.Test;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 *
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 * (若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 */
public class T009 {

    class CQueue {
        private Stack<Integer> in;
        private Stack<Integer> out;

        public CQueue() {
            in = new Stack<>();
            out = new Stack<>();
        }

        public void appendTail(int value) {
            in.push(value);
        }

        public int deleteHead() {
            if (!out.isEmpty()) {
                return out.pop();
            }
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
            if (!out.isEmpty()) {
                return out.pop();
            }
            return -1;
        }
    }

    @Test
    public void test() {

    }

}
