package com.heller.ch05;

/**
 * 以最大堆为例
 */
public class Heap {

    private static final int MAX_VALUE = Integer.MAX_VALUE;

    private int maxSize;    // 堆的最大容量
    private int[] data;     // 存放元素的数组（第一个元素不存值，作为哨兵）
    private int size;       // 当前堆中的元素个数

    /**
     * 建一个空堆
     *
     * @param maxSize 堆的最大容量
     */
    public Heap(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
        size = 0;
        data[0] = MAX_VALUE;    // 第一个元素不存值，作为哨兵
    }

    /**
     * 判断堆是否已满
     */
    public boolean isFull() {
        return this.size == maxSize;
    }

    /**
     * 判断堆是否为空
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 插入一个新的元素到堆里
     */
    public void insert(int element) {
        if (isFull()) {
            throw new RuntimeException("堆已满");
        }
        int i = ++size; // i指向插入后堆中的最后一个元素的位置
        for (; element > data[i / 2]; i /= 2) {
            data[i] = data[i / 2];    // 上滤element
        }
        data[i] = element;
    }

    /**
     * 删除元素
     */
    public int delete() {
        if (isEmpty()) {
            throw new RuntimeException("堆为空");
        }
        int max = data[1];  // 取出第一个元素，作为要删除和返回的值
        /* 用最大堆中最后一个元素从根结点开始向上过滤下层结点 */
        int x = data[size--];
        int parent = 1;
        for (int child; parent * 2 <= size; parent = child) {
            child = parent * 2;
            if (child != size && data[child] < data[child + 1]) {
                child++;
            }
            if (x >= data[child]) {
                break;
            } else {
                data[parent] = data[child];
            }
        }
        data[parent] = x;

        return max;
    }

}
