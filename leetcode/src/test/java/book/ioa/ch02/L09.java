package book.ioa.ch02;

import java.util.Stack;

import org.junit.Test;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 */
public class L09 {

    class CQueue {

        private Stack<Integer> sIn = new Stack<>();
        private Stack<Integer> sOut = new Stack<>();

        public CQueue() {
        }

        public void appendTail(int value) {
            sIn.push(value);
        }

        public int deleteHead() {
            if (sOut.isEmpty()) {
                while(!sIn.isEmpty()) {
                    sOut.push(sIn.pop());
                }
            }
            if (sOut.isEmpty()) {
                return -1;
            }
            return sOut.pop();
        }
    }

    /**
     * Your CQueue object will be instantiated and called as such:
     * CQueue obj = new CQueue();
     * obj.appendTail(value);
     * int param_2 = obj.deleteHead();
     */

    @Test
    public void testL09() {

    }

}
