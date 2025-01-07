package util;

/**
 * 一个简单的动态数组接口
 */
public interface DynamicList<E> {

    /**
     * 添加一个元素 (添加在尾部)
     **/
    boolean add(E e);

    /**
     * 在指定位置添加一个元素
     **/
    void add(int index, E e);

    /**
     * 删除指定位置的元素
     **/
    E remove(int index);

    /**
     * 删除指定元素
     **/
    boolean remove(E e);

    /**
     * 删除尾部元素
     */
    E removeLast();

    /**
     * 获取指定位置的元素
     */
    E get(int index);

    /**
     * 为指定位置的元素赋值
     */
    void set(int index, E e);

    /**
     * 获取元素个数
     */
    int size();

    /**
     * 判断是否为空
     */
    boolean isEmpty();

    /**
     * 清空列表
     */
    void clear();

    /**
     * 查找元素的索引
     */
    int indexOf(E e);

    /**
     * 判断是否包含某个元素
     */
    boolean contains(E e);

}
