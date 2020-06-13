package sword;

import java.util.Stack;

/**
 09. 用两个栈实现队列

 进队时，每次往 stack1里压入即可
 出队时，如果 stack2 有元素，直接从stack2 栈顶弹出元素即可
    如果没有，而 stack1中有元素，将stack1元素，依次弹出，压入 stack2，再从stack2 弹出栈顶元素即可

 注意，题目要求 若队列中没有元素，deleteHead 操作返回 -1
 */
public class S009 {

    class CQueue {
        private Stack stack1 = new Stack<Integer>();
        private Stack stack2 = new Stack<Integer>();

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            if (stack2.isEmpty()) {
                return -1;
            }
            return (int)stack2.pop();
        }
    }

}
