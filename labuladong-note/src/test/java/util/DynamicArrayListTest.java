package util;

public class DynamicArrayListTest extends DynamicListTest {
    /**
     * 测试 DynamicArrayList
     */
    @Override
    protected DynamicList<Integer> createList() {
        return new DynamicArrayList<>();
    }

}
