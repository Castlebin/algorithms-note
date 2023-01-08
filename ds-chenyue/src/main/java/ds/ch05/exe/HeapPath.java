package ds.ch05.exe;

import java.util.Scanner;

/*
05-树7 堆中的路径 (25分)
将一系列给定数字插入一个初始为空的小顶堆H[]。随后对任意给定的下标i，打印从H[i]到根结点的路径。

输入格式:
每组测试第1行包含2个正整数N和M(≤1000)，分别是插入元素的个数、以及需要打印的路径条数。
下一行给出区间[-10000, 10000]内的N个要被插入一个初始为空的小顶堆的整数。最后一行给出M个下标。

输出格式:
对输入中给出的每个下标i，在一行中输出从H[i]到根结点的路径上的数据。数字间以1个空格分隔，行末不得有多余空格。

输入样例:
5 3
46 23 26 24 10
5 4 3

输出样例:
24 23 10
46 23 10
26 10
 */
public class HeapPath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String[] meta = firstLine.split("\\s+");
        int maxSize = Integer.parseInt(meta[0]);
        int repeatTimes = Integer.parseInt(meta[1]);
        String dataLine = sc.nextLine();
        String[] data = dataLine.split("\\s+");
        MinHeap minHeap = new MinHeap(maxSize);
        for (int i = 0; i < maxSize; i++) {
            minHeap.insert(Integer.parseInt(data[i]));
        }
        String nodeLine = sc.nextLine();
        String[] nodes = nodeLine.split("\\s+");
        for (int i = 0; i < repeatTimes; i++) {
            StringBuilder sb = new StringBuilder();
            int node = Integer.parseInt(nodes[i]);
            for (int pos = node; pos > 0; pos /= 2) {
                sb.append(minHeap.data[pos] + " ");
            }
            System.out.println(sb.toString().trim());
        }
    }

    static class MinHeap {
        int[] data;
        int size;
        int maxSize;

        public MinHeap(int maxSize) {
            this.maxSize = maxSize;
            this.data = new int[maxSize + 1];
            // 第一个元素不存值，作为哨兵，存一个最小的元素
            data[0] = Integer.MIN_VALUE;
        }

        public void insert(int item) {
            if (isFull()) {
                throw new RuntimeException("堆已满");
            }
            int pos = ++size;
            for (; data[pos/2] > item; pos /= 2) {
                data[pos] = data[pos/2];
            }
            data[pos] = item;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size >= maxSize;
        }
    }
}
