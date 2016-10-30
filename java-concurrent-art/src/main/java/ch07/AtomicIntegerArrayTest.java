package ch07;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {
    static int[] arr = new int[]{1, 2};
    static AtomicIntegerArray aiArr = new AtomicIntegerArray(arr);// 将传入的数据复制了一份作为自己内部的数据结构，不会改变原来传入的数组

    public static void main(String[] args) {
        aiArr.getAndSet(0, 3);// 不会改变原来传入的数组
        System.out.println(aiArr.get(0));
        System.out.println(arr[0]);
    }
}
