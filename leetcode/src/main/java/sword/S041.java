package sword;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 剑指 Offer 41. 数据流中的中位数
 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

 例如，
 [2,3,4] 的中位数是 3

 [2,3] 的中位数是 (2 + 3) / 2 = 2.5

 设计一个支持以下两种操作的数据结构：

 void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 double findMedian() - 返回目前所有元素的中位数。
 */
public class S041 {

    /**
     * 利用 一个最大堆、一个最小堆 实现 任何时候，都能快速的取数据流中的中位数
     */
    class MedianFinder {

        private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        /** initialize your data structure here. */
        public MedianFinder() {

        }

        public void addNum(int num) {
            int size = maxHeap.size() + minHeap.size();
            if ((size & 1) == 0) {
                if (maxHeap.size() == 0 || num >= maxHeap.peek()) {
                    minHeap.add(num);
                } else {
                    minHeap.add(maxHeap.remove());
                    maxHeap.add(num);
                }
            } else {
                if (minHeap.size() == 0 || num < minHeap.peek()) {
                    maxHeap.add(num);
                } else {
                    maxHeap.add(minHeap.remove());
                    minHeap.add(num);
                }
            }
        }

        public double findMedian() {
            int size = maxHeap.size() + minHeap.size();
            if (size <= 0) {
                throw new RuntimeException("没有元素");
            }
            if ( (size & 1) == 1) {
                return minHeap.peek();
            } else {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
        }

    }

    @Test
    public void test() {

    }

}
