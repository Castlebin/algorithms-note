package leetcode.N1_N99;

import org.junit.Test;

import leetcode.base.ListNode;

/**
 * 83. Remove Duplicates from Sorted List
 * 83. 删除排序链表中的重复元素
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表(已经不包含重复元素)
 */
public class T83 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 快慢指针，fast 指针寻找下一个不重复的元素
        ListNode fast = head, slow = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        // fast 已经走到底了，说明 slow 是最后一个不重复的元素了，所以，slow 以后的节点都丢掉
        slow.next = null;
        return head;
    }

    @Test
    public void test() {

    }

}
