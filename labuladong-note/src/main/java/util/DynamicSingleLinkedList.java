package util;

public class DynamicSingleLinkedList<E> implements DynamicList<E> {
    private Node<E> dummyHead;
    private Node<E> tail;
    private int size;

    public DynamicSingleLinkedList() {
        this.dummyHead = new Node<>(null, null);
    }

    private static class Node<E> {
        private E val;
        private Node<E> next;

        public Node(E val, Node<E> next) {
            this.val = val;
            this.next = next;
        }
    }
    /**
     * 添加一个元素 (添加在尾部)
     */
    @Override
    public boolean add(E e) {
        Node<E> prev;
        if (size == 0) {
            prev = dummyHead;
        } else {
            prev = tail;
        }
        Node<E> node = new Node<>(e, null);
        prev.next = node;
        tail = node;
        size++;
        return true;
    }

    /**
     * 在指定位置添加一个元素
     */
    @Override
    public void add(int index, E e) {
        checkPositionIndex(index);
        if (index == size) {
            add(e);
        } else if (index == 0) {
            Node<E> node = new Node<>(e, dummyHead.next);
            dummyHead.next = node;
            size++;
        } else {
            Node<E> prev = getNode(index - 1);
            Node<E> node = new Node<>(e, prev.next);
            prev.next = node;
            size++;
        }
    }

    private Node<E> getNode(int index) {
        checkElementIndex(index);
        Node<E> cur = dummyHead;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur;
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

        Node<E> prev;
        if (index == 0) {
            prev = dummyHead;
        } else {
            prev = getNode(index - 1);
        }
        Node<E> node = prev.next;
        prev.next = node.next;
        if (index == size - 1) {
            tail = prev;
        }
        size--;
        return node.val;
    }

    /**
     * 删除指定元素
     */
    @Override
    public boolean remove(Object o) {
        Node<E> prev = dummyHead;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                Node<E> cur = prev.next;
                if (cur.val == null) {
                    prev.next = cur.next;
                    size--;
                    if (i == size - 1) {
                        tail = prev;
                    }
                    return true;
                }
                prev = cur;
            }
        } else {
            for (int i = 0; i < size; i++) {
                Node<E> cur = prev.next;
                if (cur.val == o) {
                    prev.next = cur.next;
                    size--;
                    if (i == size - 1) {
                        tail = prev;
                    }
                    return true;
                }
                prev = cur;
            }
        }
        return false;
    }

    /**
     * 获取指定位置的元素
     */
    @Override
    public E get(int index) {
        return getNode(index).val;
    }

    /**
     * 为指定位置的元素赋值。返回值为原来的元素（oldValue）
     */
    @Override
    public E set(int index, E e) {
        Node<E> node = getNode(index);
        E oldVal = node.val;
        node.val = e;
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
        dummyHead.next = null;
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
