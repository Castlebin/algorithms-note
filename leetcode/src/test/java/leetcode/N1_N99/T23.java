package leetcode.N1_N99;

import java.util.PriorityQueue;

import org.junit.Test;

import leetcode.base.ListNode;


/**
 * 23. Merge k Sorted Lists
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class T23 {

    /**
     * 使用一个堆（优先队列）保存所有链表当前的位置
     * (其实相当于利用 优先队列 来了一次排序)
     * 时间复杂度 Nlog(n) ，空间复杂度 O(n)     n 为 lists 长度，N 为所有节点的总数目
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 使用一个优先队列，来保存所有链表指针遍历到的当前位置，并且，能够方便获取到其中的最小元素
        PriorityQueue<ListNode> heap = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
        for (ListNode list : lists) {
            if (list != null) {
                heap.add(list);
            }
        }
        // 使用一个哑结点来简化操作
        ListNode dummy = new ListNode(-1), p = dummy;
        while (!heap.isEmpty()) {
            // 从最小堆中取出堆顶元素
            ListNode min = heap.poll();
            // 相当于向后移动了这个链表的指针
            if (min.next != null) {
                heap.add(min.next);
            }
            p.next = min;
            p = p.next;
        }
        return dummy.next;
    }

    @Test
    public void test() {

    }

}
