package ch02;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 2.4.4 直接内存溢出(使用unsafe分配内存)
 * 直接内存分配
 *  VM args:
 *      -Xms20M -XX:MaxDirectMemorySize=10M  -XX:+HeapDumpOnOutOfMemoryError
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
