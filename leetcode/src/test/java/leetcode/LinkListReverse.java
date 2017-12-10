package leetcode;

import org.junit.Test;

public class LinkListReverse {

    @Test
    public void testReverse() {
        int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7};
        LinkListNode linkList = LinkListNode.create(arr);
        System.out.println(linkList);
        linkList = linkList.reverse();
        System.out.println(linkList);
    }

}
