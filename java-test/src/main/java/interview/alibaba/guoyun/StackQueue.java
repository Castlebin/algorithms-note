package interview.alibaba.guoyun;

import java.util.Random;
import java.util.Stack;

/**
 * 4. 使用两个栈来实现队列的一些操作。
 * @author Lgy
 * @desc 栈实现队列
 * 队列应支持push(element)，pop() 和 top()，其中pop是弹出队列中的第一个(最前面的)元素。
 * pop和top方法都应该返回第一个元素的值。
 * 样例：比如push(1), pop(), push(2), push(3), top(), pop()，你应该返回1，2和2
 * 要求：仅使用两个栈来实现它，不使用任何其他数据结构，push，pop 和 top的复杂度都应该是均摊O(1)的
 * @date 2020-12-21
 */
public class StackQueue<E> {
    private Stack<E> inSt;
    private Stack<E> outSt;

    public StackQueue() {
        inSt = new Stack<>();
        outSt = new Stack<>();
    }

    /**
     * 入队
     * @param element
     */
    public void push(E element){
        inSt.push(element);
    }

    /**
     * 出队 并删除队尾元素
     * @return
     */
    public E pop(){
        if(outSt.isEmpty()){
            while (!inSt.isEmpty()){
                outSt.push(inSt.pop());
            }
        }
        return outSt.pop();
    }

    /**
     * 出队 返回队尾元素
     * @return
     */
    public E top(){
        if(outSt.isEmpty()){
            while (!inSt.isEmpty()){
                outSt.push(inSt.pop());
            }
        }
        return outSt.peek();
    }

    public static void main(String[] args) {
        StackQueue<Integer> queue = new StackQueue<>();
        Random random = new Random();
        for (int i = 0; i <10; i++) {
            queue.push(i*100);
            if(i%3 == random.nextInt(3)){
                System.out.println(i+"-top:"+queue.top());
                System.out.println(i+"-pop:"+queue.pop());
            }
        }
    }
}
