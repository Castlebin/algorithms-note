package ds.ch02.linkedlist;

import org.junit.Assert;
import org.junit.Test;

public class NodeUtilTest {

    @Test
    public void testGenerateLinkedListFromArray() {
        Assert.assertEquals(null, NodeUtil.generateLinkedListFromArray(new int[]{}));
        Assert.assertEquals(null, NodeUtil.generateLinkedListFromArray(null));
    }

    @Test
    public void testToString() {
        Node linkedList = NodeUtil.generateLinkedListFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println(NodeUtil.toString(linkedList));
    }
}
