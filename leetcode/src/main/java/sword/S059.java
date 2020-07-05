package sword;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 剑指 Offer 59 - I. 滑动窗口的最大值

 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

 提示：
 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
*/
public class S059 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[]{};
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < k; i++) {
            heap.add(nums[i]);
        }
        int[] result = new int[nums.length - k + 1];
        for (int i = 0, t = 0; i < result.length; ) {
            result[i++] = heap.peek();
            heap.remove(nums[t++]);
            if (t + k - 1 < nums.length) {
                heap.add(nums[t + k - 1]);
            }
        }
        return result;
    }

    /**
     * 一个大顶堆
     */
    public class Heap {
        int[] data;
        int size;

        public Heap(int maxSize) {
            this.data = new int[maxSize];
        }

        public Heap(int[] array, int begin, int end, int maxSize) {
            this.data = new int[maxSize];
            this.size = end - begin + 1;
            System.arraycopy(array, begin, data, 0, size);

            // 从最后一个有儿子的节点开始调整堆
            int parent = (size - 1 - 1) / 2;
            while (parent >= 0) {
                sink(parent);
                parent--;
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == data.length;
        }

        public void insert(int element) {
            if (isFull()) {
                throw new RuntimeException("堆已满");
            }
            data[size++] = element;
            // 调整堆
            swim(size);
        }

        public int delete() {
            if (isEmpty()) {
                throw new RuntimeException("堆为空");
            }
            int element = data[0];
            sink(0);
            size--;
            return element;
        }

        private void swim(int index) {
            int tmp = data[index];
            int parent = (index - 1) / 2;
            while (parent >= 0 && tmp > data[parent]) {
                data[index] = data[parent];
                index = parent;
                parent = (index - 1) / 2;
            }
            data[index] = tmp;
        }

        private void sink(int index) {
            int tmp = data[index];
            int child = index * 2 + 1;
            while (child < size) {
                if (child + 1 < size && data[child + 1] > data[child]) {
                    child++;
                }
                if (tmp >= data[child]) {
                    break;
                } else {
                    data[index] = data[child];
                    index = child;
                    child = index * 2 + 1;
                }
            }
            data[index] = tmp;
        }

    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
    }

}
