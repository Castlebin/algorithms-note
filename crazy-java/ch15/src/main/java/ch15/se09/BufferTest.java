package ch15.se09;

import java.nio.CharBuffer;

public class BufferTest {
    public static void main(String[] args) {
        // 创建Buffer, capacity为8
        CharBuffer buffer = CharBuffer.allocate(8);
        System.out.println("初始时：");
        System.out.println("capacity: " + buffer.capacity());
        System.out.println("limit: " + buffer.limit());
        System.out.println("position: " + buffer.position());

        // 放入元素
        buffer.put('a');
        buffer.put('b');
        buffer.put('c');

        System.out.println("加入三个元素后：");
        System.out.println("limit: " + buffer.limit());
        System.out.println("position: " + buffer.position());

        buffer.flip();
        System.out.println("执行flip()方法后：");
        System.out.println("limit: " + buffer.limit());
        System.out.println("position: " + buffer.position());
        // 取出一个元素
        System.out.println("取出元素：" + buffer.get() + "，此时position=" + buffer.position());
        System.out.println("取出元素：" + buffer.get() + "，此时position=" + buffer.position());

        // 调用clear()方法
        buffer.clear();
        System.out.println("执行clear()方法之后：");
        System.out.println("limit: " + buffer.limit());
        System.out.println("position: " + buffer.position());

        // 执行clear()方法之后，其实缓冲区中的内容并没有被清除
        // clean()和flip()方法都只是去改变limit, position指针位置
        // 其中clear()方法为再次向Buffer中放入数据做准备
        // flip()方法为从Buffer中读取数据做准备
        System.out.println("执行clear()方法后，缓冲区中的内容并没有实际被删除：第三个元素为：" + buffer.get(2));
        System.out.println("上面执行绝对读取，并不会影响position指针位置，position=" + buffer.position());
    }
}
