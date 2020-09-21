package sword;

import org.junit.Test;
import common.ListNode;

/**
 面试题25. 合并两个排序的链表
 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 */
public class S025 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode first, second;
        if (l1.val <= l2.val) {
            first = l1;
            second = l2;
        } else {
            first = l2;
            second = l1;
        }
        ListNode prev = first;
        ListNode firstCur = first.next;
        ListNode secondCur = second;
        while (firstCur != null && secondCur != null) {
            if (firstCur.val <= secondCur.val) {
                firstCur = firstCur.next;
            } else {
                // 将 secondCur 插入 prev 后面
                ListNode secondCurNext = secondCur.next;
                secondCur.next = prev.next;
                prev.next = secondCur;
                secondCur = secondCurNext;
            }
            prev = prev.next;
        }

        // 如果 firstCur == null，但是 secondCur != null，那么应该将 secondCur 插入尾部
        if (firstCur == null) {
            prev.next = secondCur;
        }

        return first;
    }

    @Test
    public void test() {
        ListNode l1 = ListNode.createListNode("124");
        ListNode l2 = ListNode.createListNode("134");
        System.out.println(mergeTwoLists(l1, l2));
    }

}
