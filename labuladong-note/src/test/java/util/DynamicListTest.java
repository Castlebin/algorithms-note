package util;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

@Ignore
public abstract class DynamicListTest {

    protected DynamicList<Integer> list;

    // 添加抽象方法，由具体的测试类实现
    protected abstract DynamicList<Integer> createList();

    @Before
    public void setUp() {
        list = createList();  // 使用工厂方法创建具体实现
    }

    @Test
    public void testAdd() {
        assertTrue(list.add(1));
        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
    }

    @Test
    public void testAddAtIndex() {
        list.add(0, 1);
        list.add(1, 2);
        list.add(1, 3);
        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(1));
        assertEquals(Integer.valueOf(2), list.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexOutOfBounds() {
        list.add(1, 1);
    }

    @Test
    public void testRemoveByIndex() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(Integer.valueOf(2), list.remove(1));
        assertEquals(2, list.size());
        assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveByIndexOutOfBounds() {
        list.remove(0);
    }

    @Test
    public void testRemoveByObject() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertTrue(list.remove(Integer.valueOf(2)));
        assertEquals(2, list.size());
        assertFalse(list.contains(2));
    }

    @Test
    public void testRemoveNonExistentObject() {
        list.add(1);
        list.add(2);
        assertFalse(list.remove(Integer.valueOf(3)));
        assertEquals(2, list.size());
    }

    @Test
    public void testGet() {
        list.add(1);
        list.add(2);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBounds() {
        list.get(0);
    }

    @Test
    public void testSet() {
        list.add(1);
        list.add(2);
        assertEquals(Integer.valueOf(2), list.set(1, 3));
        assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBounds() {
        list.set(0, 1);
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());
        list.add(1);
        assertEquals(1, list.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testClear() {
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIndexOf() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(1, list.indexOf(2));
        assertEquals(-1, list.indexOf(4));
    }

    @Test
    public void testContains() {
        list.add(1);
        list.add(2);
        assertTrue(list.contains(1));
        assertFalse(list.contains(3));
    }

    @Test
    public void testResize() {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        assertEquals(20, list.size());
        for (int i = 0; i < 20; i++) {
            assertEquals(Integer.valueOf(i), list.get(i));
        }
    }

    @Test
    public void testShrink() {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        for (int i = 0; i < 15; i++) {
            list.remove(0);
        }
        assertEquals(5, list.size());
        for (int i = 0; i < 5; i++) {
            assertEquals(Integer.valueOf(15 + i), list.get(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtInvalidIndex() {
        list.add(1);
        list.add(5, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class) 
    public void testAddAtNegativeIndex() {
        list.add(1);
        list.add(-1, 2);
    }

    @Test
    public void testSetElement() {
        list.add(1);
        list.add(2);
        assertEquals(Integer.valueOf(1), list.set(0, 3));
        assertEquals(Integer.valueOf(3), list.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetAtInvalidIndex() {
        list.add(1);
        list.set(5, 2);
    }

    @Test
    public void testEmptyListOperations() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertFalse(list.contains(1));
        assertEquals(-1, list.indexOf(1));
    }

    @Test
    public void testAddRemoveAdd() {
        list.add(1);
        list.remove(0);
        list.add(2);
        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(2), list.get(0));
    }
}
