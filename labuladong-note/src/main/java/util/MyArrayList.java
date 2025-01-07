package util;

/**
 * 一个简单的动态数组
 * 扩缩容策略：
 * 1. 当数组满时，扩容为原来的 2 倍
 * 2. 当元素个数为数组容量的 1/4 时，缩容为原来的 1/2  （为了避免频繁扩缩容）
 */
public class MyArrayList<E> implements DynamicList<E> {
    private static final int DEFAULT_CAPACITY = 1;

    /**
     * 底层存放元素的数组
     */
    private E[] data;
    /**
     * 记录元素个数
     */
    private int size;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 添加一个元素 (添加在尾部)
     */
    @Override
    public boolean add(E e) {
        // 判断是否需要扩容
        if (size == data.length) {
            // 扩容为原来的 2 倍
            resize(2 * data.length);
        }
        data[size++] = e;
        return true;
    }

    private void resize(int newSize) {
        E[] newData = (E[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 在指定位置添加一个元素
     */
    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }
        // 判断是否需要扩容
        if (size == data.length) {
            // 扩容为原来的 2 倍
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 删除指定位置的元素
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null; // loitering objects != memory leak
        // 判断是否需要缩容
        if (size == data.length / 4 && data.length / 2 != 0) {
            // 缩容为原来的 1/2
            resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 删除指定元素
     */
    @Override
    public boolean remove(E e) {
        int index = indexOf(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * 删除尾部元素
     */
    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 获取指定位置的元素
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    /**
     * 为指定位置的元素赋值
     */
    @Override
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        data[index] = e;
    }

    /**
     * 获取元素个数
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 判断是否为空
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空列表
     */
    @Override
    public void clear() {
        size = 0;
    }

    /**
     * 查找元素的索引
     */
    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 判断是否包含某个元素
     */
    @Override
    public boolean contains(E e) {
        return indexOf(e) != -1;
    }

}
