package com.heller.util;

/**
 * 一个简单的动态数组（列表）实现类
 * 简单的扩缩容策略：
 * 1. 当元素个数达到数组容量时，扩容为原来的 2 倍
 * 2. 当元素个数小于数组容量的 1/4 时，缩容为原来的 1/2 （避免频繁扩缩容）
 */
public class DynamicArrayList<E> implements DynamicList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};

    private Object[] elementData;
    private int size;

    public DynamicArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * 添加一个元素 (添加在尾部)
     */
    @Override
    public boolean add(E e) {
        // 如果数组已满，扩容 (扩容时，需要将原数组的元素复制到新数组)
        // （注意，要考虑初始时，elementData.length 为 0 的情况，扩容时直接乘以 2 显然不行，因为还是 0）
        if (size == elementData.length) {
            resize(elementData.length * 2);
        }
        elementData[size] = e;
        size++;
        return true;
    }

    /**
     * 在指定位置添加一个元素
     */
    @Override
    public void add(int index, E e) {
        checkPositionIndex(index);

        // 如果数组已满，扩容 (扩容时，需要将原数组的元素复制到新数组)
        if (size == elementData.length) {
            resize(elementData.length * 2);
        }
        // 将 index 位置及其后面的元素后移一位
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        // 将 e 插入到 index 位置
        elementData[index] = e;
        size++;
    }

    private void resize(int minCapacity) {
        if (minCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + minCapacity);
        }
        if (minCapacity == 0) {
            minCapacity = 1;
        }
        Object[] newElementData = new Object[minCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * 删除指定位置的元素
     */
    @Override
    public E remove(int index) {
        checkElementIndex(index);

        E oldValue = elementData(index);
        // 将 index 位置后面的元素前移一位
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        // 将最后一个元素置为 null，并将 size 减 1
        elementData[size - 1] = null;
        size--;
        // 如果元素个数小于数组容量的 1/4，缩容
        if (size < elementData.length / 4) {
            resize(elementData.length / 2);
        }
        return oldValue;
    }

    /**
     * 删除指定元素
     */
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * 获取指定位置的元素
     */
    @Override
    public E get(int index) {
        checkElementIndex(index);

        return elementData(index);
    }

    /**
     * 获取指定位置的元素
     */
    // Suppress unchecked cast warning
    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * 为指定位置的元素赋值
     */
    @Override
    public E set(int index, E e) {
        checkElementIndex(index);

        E oldValue = elementData(index);
        elementData[index] = e;
        return oldValue;
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
        elementData = EMPTY_ELEMENTDATA;
        size = 0;
    }

    /**
     * 查找元素的索引
     */
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                // 注意这里使用 equals 方法判断是否相等 （而不是 ==），因为元素类型是泛型 E （参考 java 标准库的实现）
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 判断是否包含某个元素
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }
}
