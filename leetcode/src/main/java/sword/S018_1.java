package sword;

import org.junit.Test;
import sword.common.ListNode;

/**
 面试题18. 删除链表的节点
 给定单向链表的头指针和一个要删除的 **节点指针** ，定义一个函数删除该节点。

 返回删除后的链表的头节点。

 一般时间复杂度需要为 O(1)   (如果是尾结点的话，为 O(N)，只能遍历，顺序查找进行删除了)

 注意，由于时间要求为O(1)，所以，只能调用者自己保证要删除的节点，确实是在链表中的，不然程序正确性不能保证
 */
public class S018_1 {

    public ListNode deleteNode(ListNode head, ListNode target) {
        if (head == null || target == null) {
            return head;
        }

        // 要求以 O(1) 的时间删除节点，所以，将下一个节点的值，复制到该节点，然后删除下一个节点即可
        // 删除变替换的思想
        // 还注意几种边界情况
        // 1. 链表不止一个节点，删除的是尾结点
        // 2. 删除的是头结点也是尾结点（即链表中只有一个节点，还要删除它）

        // 普通情况：链表不止一个节点，并且删除的不是尾结点（删除变替换，然后删掉下一个节点  时间复杂度 O(N)）
        if (target.next != null) {
            ListNode tobeDelete = target.next;
            target.val = tobeDelete.val;
            target.next = tobeDelete.next;
            tobeDelete.next = null;
        } else if (head == target) {
            // 链表只有一个节点，并且，删除的是头结点（同时也是尾结点）
            return null;
        } else {
            // 有多个节点，并且删除的是尾结点，只能常规删除
            ListNode prev = head;
            ListNode cur = head.next;
            while (cur != null) {
                if (cur == target) {
                    prev.next = cur.next;
                    cur.next = null;
                    break;
                }
                prev = cur;
                cur = cur.next;
            }
        }

        return head;
    }

    @Test
    public void test() {

    }

}
