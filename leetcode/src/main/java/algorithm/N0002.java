package algorithm;

import org.junit.Test;

/**
 * 2. Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class N0002 {

    /**
     * 相当于一个简单的加法器实现
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        ListNode nextNode;
        ListNode aCur = l1;
        ListNode bCur = l2;
        int a, b, sum;
        int carry = 0;

        do {
            a = aCur.val;
            b = bCur.val;
            sum = a + b + carry;
            if (sum >= 10) {
                carry = sum / 10;
                sum %= 10;
            } else {
                carry = 0;
            }
            cur.val = sum;

            aCur = aCur.next;
            bCur = bCur.next;
            if (aCur != null && bCur != null) {
                nextNode = new ListNode(0);
                cur.next = nextNode;
                cur = nextNode;
            }
        } while (aCur != null && bCur != null);

        ListNode left = aCur == null ? bCur : aCur;
        while (left != null && carry > 0) {
            sum = left.val + carry;
            if (sum >= 10) {
                carry = sum / 10;
                sum %= 10;
            } else {
                carry = 0;
            }
            nextNode = new ListNode(sum);
            cur.next = nextNode;
            cur = nextNode;
            left = left.next;
        }
        if (carry <= 0) {
            cur.next = left;
        } else {
            nextNode = new ListNode(carry);
            cur.next = nextNode;
            cur = nextNode;
        }

        return head;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        ListNode head = new ListNode(0);
        ListNode cur = head;
        ListNode next;
        int carry = 0;
        while (l1 != null || l2 != null || carry > 0) {
            int sum = ((l1 == null) ? 0 : l1.val) + ((l2 == null) ? 0 : l2.val) + carry;
            cur.val = sum % 10;
            carry = sum / 10;
            l1 = (l1 == null) ? null : l1.next;
            l2 = (l2 == null) ? null : l2.next;
            if (l1 != null || l2 != null || carry > 0) {
                next = new ListNode(0);
                cur.next = next;
                cur = next;
            }
        }

        return head;
    }

    /**
     * 为了方便计算，增加一个前置空节点，算法变得简洁了许多
     */
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode prev = head;
        int carry = 0;
        ListNode cur;
        while (l1 != null || l2 != null || carry > 0) {
            int sum = ((l1 == null) ? 0 : l1.val) + ((l2 == null) ? 0 : l2.val) + carry;
            cur = new ListNode(sum % 10);
            carry = sum / 10;
            prev.next = cur;
            prev = cur;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        return head.next;
    }

    @Test
    public void test() {
        ListNode l1 = ListNode.createListNode("456789");
        System.out.println(l1);
        ListNode l2 = ListNode.createListNode("56789");
        System.out.println(l2);

        ListNode l3 = addTwoNumbers(l1, l2);
        System.out.println(l3);

        System.out.println(addTwoNumbers2(l1, l2));
        System.out.println(addTwoNumbers3(l1, l2));
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static ListNode createListNode(String s) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        ListNode next;
        for (int i = 0; i < s.length(); i++) {
            cur.val = Integer.parseInt(s.substring(i, i + 1));
            if (i < s.length() - 1) {
                next = new ListNode(0);
                cur.next = next;
                cur = next;
            }
        }
        return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        do {
            sb.append(cur.val);
            cur = cur.next;
        } while (cur != null);

        return sb.toString();
    }
}
