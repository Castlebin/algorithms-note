package ds.ch05;

/**
 * 以最大堆为例
 */
public class Heap {

    private static final int MAX_VALUE = Integer.MAX_VALUE;

    private int[] data;     // 存放元素的数组（第一个元素不存值，作为哨兵）
    private int size;       // 当前堆中的元素个数

    /**
     * 建一个空堆
     *
     * @param maxSize 堆的最大容量
     */
    public Heap(int maxSize) {
        this.data = new int[maxSize + 1]; // 第一个元素不存值，作为哨兵（最大堆）
        this.size = 0;
        this.data[0] = MAX_VALUE;    // 第一个元素不存值，作为哨兵（最大堆）
    }

    /**
     * 判断堆是否已满
     */
    public boolean isFull() {
        return this.size == data.length - 1;
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
            data[i] = data[i / 2];    // 上浮 element
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
        /* 用最大堆中最后一个元素从根结点开始 下沉 */
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

    /*----------- 建造最大堆 -----------*/
    public Heap(int[] data, int maxSize) {
        this.data = new int[maxSize + 1];
        this.data[0] = MAX_VALUE;
        this.size = data.length;
        System.arraycopy(data, 0, this.data, 1, data.length);

        // 已经初始化好了堆，要开始调整堆中存储元素的数组了
        // 从最后一个有儿子的节点开始调整堆，使满足最大堆的有序性
        // 反向依次调整每个有儿子的节点，对每个节点做 下沉 操作 ，操作完所有有儿子的节点，那么堆就调整好了
        for (int i = this.size / 2; i > 0; i--) {
            int parent = i;
            int x = this.data[parent];
            for (int child; parent * 2 <= this.size; parent = child) { // 下沉
                child = parent*2;
                if (child != this.size && this.data[child] < this.data[child + 1]) {
                    child++;
                }
                if (x >= this.data[child]) {
                    break;
                } else {
                    this.data[parent] = this.data[child];
                }
            }
            this.data[parent] = x;
        }
    }

}
