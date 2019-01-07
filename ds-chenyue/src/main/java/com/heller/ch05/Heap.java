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

}
