package sword.sa;

import org.junit.Test;

import java.util.*;

/**
 剑指 Offer 59 - II. 队列的最大值
 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。

 若队列为空，pop_front 和 max_value 需要返回 -1
 */
public class T059 {

    class MaxQueue {

        private Queue<Integer> dataQueue = new LinkedList<>();
        private Deque<Integer> deque = new LinkedList<>();

        public int max_value() {
            if (deque.isEmpty()) {
                return -1;
            }
            return deque.peekLast();
        }

        public void push_back(int value) {
            dataQueue.add(value);
            // value >= 当前的最大值就放进去，省去用一个 map 去计数！！
            if (deque.isEmpty() || value >= deque.peekLast()) {
                deque.add(value);
            }
        }

        public int pop_front() {
            if (dataQueue.isEmpty()) {
                return -1;
            }
            int value = dataQueue.remove();
            if (!deque.isEmpty() && deque.peekFirst() == value) {
                deque.pop();
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
    }

    @Test
    public void test() {

    }

}
