package sword;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 剑指 Offer 59 - II. 实现一个能够随时取到最大值的队列

 请定义一个队列并实现函数 max_value 得到队列里的最大值，
 要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。

 若队列为空，pop_front 和 max_value 需要返回 -1
*/
public class S059 {

    class MaxQueue {

        private Queue<Integer> dataQueue = new LinkedList<>();
        private Deque<Integer> deque = new LinkedList<>();

        public int max_value() {
            if (!deque.isEmpty()) {
                return deque.peekLast();
            }
            return -1;
        }

        public void push_back(int value) {
            dataQueue.add(value);
            if (deque.isEmpty() || value >= deque.peekLast()) {
                deque.add(value);
            }
        }

        public int pop_front() {
            if (!dataQueue.isEmpty()) {
                int value = dataQueue.remove();
                if (!deque.isEmpty() && deque.peekFirst() == value) {
                    deque.pop();
                    // 为了保证能每次取到最大值，当deque为空时，必须重新调整
                    if (deque.isEmpty() && !dataQueue.isEmpty()) {
                        for (Integer v : dataQueue) {
                            if (deque.isEmpty() || v >= deque.peekLast()) {
                                deque.add(v);
                            }
                        }
                    }
                }
                return value;
            }
            return -1;
        }
    }

    @Test
    public void test() {

    }

}
