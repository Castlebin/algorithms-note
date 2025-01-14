package util;

/**
 * 双向链表
 */
public class DynamicLinkedList<E> implements DynamicList<E> {
    /**
     * 为了方便操作，定义私有属性，虚拟头结点、尾结点
     */
    private final Node<E> dummyHead;
    private final Node<E> dummyTail;

    /**
     * 方便操作，存放实际元素的个数
     */
    private int size;

    public DynamicLinkedList() {
        this.dummyHead = new Node<>(null, null, null);
        this.dummyTail = new Node<>(null, null, null);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    /**
     * 定义链表节点，这里是双向链表
     */
    private static class Node<E> {
        private E val;
        private Node<E> prev;
        private Node<E> next;

        Node(E val, Node<E> prev, Node<E> next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 添加一个元素 (添加在尾部)
     */
    @Override
    public boolean add(E e) {
        Node<E> prev = dummyTail.prev;
        Node<E> next = dummyTail;
        Node<E> node = new Node<>(e, prev, next);
        prev.next = node;
        next.prev = node;
        size++;
        return true;
    }

    /**
     * 在指定位置添加一个元素
     */
    @Override
    public void add(int index, E e) {
        checkPositionIndex(index);

        Node<E> prev;
        if (index == 0) {
            prev = dummyHead;
        } else if (index == size) {
            prev = dummyTail.prev;
        } else {
            prev = getNode(index).prev;
        }
        Node<E> next = prev.next;
        Node<E> node = new Node<>(e, prev, next);
        prev.next = node;
        next.prev = node;
        size++;
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

        Node<E> node = getNode(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        prev.next = next;
        next.prev = prev;

        size--;

        return node.val;
    }

    private Node<E> getNode(int index) {
        Node<E> cur;
        if (index <= size / 2) {
            cur = dummyHead.next;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
        } else {
            cur = dummyTail;
            for (int i = size - index - 1; i >= 0; i--) {
                cur = cur.prev;
            }
        }
        return cur;
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

        return getNode(index).val;
    }

    /**
     * 为指定位置的元素赋值。返回值为原来的元素（oldValue）
     */
    @Override
    public E set(int index, E e) {
        checkElementIndex(index);
        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        E oldVal = cur.val;
        cur.val = e;
        return oldVal;
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
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        size = 0;
    }

    /**
     * 查找元素的索引
     */
    @Override
    public int indexOf(Object o) {
        Node<E> cur = dummyHead.next;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (cur.val == null) {
                    return i;
                }
                cur = cur.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (cur.val.equals(o)) {
                    return i;
                }
                cur = cur.next;
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
