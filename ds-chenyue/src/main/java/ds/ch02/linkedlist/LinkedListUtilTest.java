package ds.ch02.linkedlist;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListUtilTest {

    @Test
    public void testGenerateLinkedListFromArray() {
        Assert.assertEquals(null, LinkedListUtil.generateLinkedListFromArray(new int[]{}));
        Assert.assertEquals(null, LinkedListUtil.generateLinkedListFromArray(null));
    }

    @Test
    public void testToString() {
        Node linkedList = LinkedListUtil.generateLinkedListFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println(LinkedListUtil.toString(linkedList));
    }
}
