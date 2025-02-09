package com.heller.util;

public class DynamicLinkedListTest extends DynamicListTest {
    /**
     * 测试 DynamicArrayListTest
     */
    @Override
    protected DynamicList<Integer> createList() {
        return new DynamicLinkedList<>();
    }

}
