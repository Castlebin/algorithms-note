package util;

public class DynamicSingleLinkedListTest extends DynamicListTest {
    /**
     * 测试 DynamicSingleLinkedList
     */
    @Override
    protected DynamicList<Integer> createList() {
        return new DynamicSingleLinkedList<>();
    }

}
