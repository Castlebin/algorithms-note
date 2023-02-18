package leetcode.N100_N199;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import leetcode.base.ListNode;


/**
 * 160. Intersection of Two Linked Lists
 * 160. 相交链表
 * <p>
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 * <p>
 * 题目数据 保证 整个链式结构中不存在环。
 * <p>
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 */
public class T160 {

    /**
     * 解法 3， 双指针
     * 时间复杂度 O(M + N)，空间复杂度 O(1)
     * <p>
     * 两个指针交替遍历两个链表，
     * <p>
     * 其实它等价于  将其中一个链表首尾相接，构成环，问题变成判断另一个链表中是否存在环路，存在的话输出环路入口点的问题
     * 重置指针这一步，相当于将链表重结构上首尾相连了。所以和拿到链表环的入口这题思路可以联系起来了
     */
    public ListNode getIntersectionNode_3(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != null || p2 != null) {
            if (p1 == p2) {
                return p1;
            }
            // p1 移动到了尾巴，就重置指向 headB。
            p1 = p1 != null ? p1.next : headB;
            // p2 移动到了尾部，就重置指向 headA
            p2 = p2 != null ? p2.next : headA;
        }
        return null;
    }
    /**
     * 解法 3 ，双指针。上面代码简化一下（等价的）
     */
    public ListNode getIntersectionNode_3_1(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            p1 = p1 != null ? p1.next : headB;
            p2 = p2 != null ? p2.next : headA;
        }
        return p1;
    }

    /**
     * 解法 2：两个指针 p1, p2. 先计算两条链表的长度，然后让 p1 和 p2 距离链表尾部的距离相同，然后齐头并进
     * 时间复杂度 O(M + N)，空间复杂度 O(1)
     */
    public ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
        int sizeA = getSize(headA);
        int sizeB = getSize(headB);
        int sizeDiff = Math.abs(sizeA - sizeB);
        ListNode p1 = headA, p2 = headB;
        // 哪个链表长一些，哪个指针就应该往下移动 sizeDiff 步，这样 p1 和 p2 距离链表尾部的距离就相同了
        if (sizeA > sizeB) {
            for (int i = 0; i < sizeDiff; i++) {
                p1 = p1.next;
            }
        } else if (sizeA < sizeB) {
            for (int i = 0; i < sizeDiff; i++) {
                p2 = p2.next;
            }
        }
        // 好了，现在 p1 、p2 距离各自的尾部的长度距离相同了。起头并进，若果相遇的话，就是相交节点
        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return null;
    }

    private int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            head = head.next;
            size++;
        }
        return size;
    }

    // 解法 1 ，最简单的，借助一个 HashSet，保存其中一个链表的数据，再遍历另一个链表，看看有没有节点相同
    // 空间复杂度 O(N)  , 时间复杂度 O(M + N)
    public ListNode getIntersectionNode_1(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        // 借助一个 HashSet，保存 链表A 的数据
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    @Test
    public void test() {

    }

}
