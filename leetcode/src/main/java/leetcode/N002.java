package leetcode;

import java.math.BigInteger;

public class N002 {

    public BigInteger getNumOfListNode(ListNode listNode) {
        return new BigInteger(getNumStrOfListNode(listNode));
    }

    public String getNumStrOfListNode(ListNode listNode) {
        return new StringBuilder(getNumStrOfListNodeReverse(listNode)).reverse().toString();
    }

    public String getNumStrOfListNodeReverse(ListNode listNode) {
        String numStr = "" + listNode.val;
        if (listNode.next == null) {
            return numStr;
        } else {
            return numStr + getNumStrOfListNodeReverse(listNode.next);
        }
    }

    public ListNode createListNodeFromNum(BigInteger num) {
        String numReverseStr = new StringBuilder(num.toString()).reverse().toString();

        char[] chars = numReverseStr.toCharArray();
        ListNode result = new ListNode(new Integer(chars[0]+""));
        ListNode n = result;
        for (int i=1; i<chars.length; i++) {
            ListNode newNode = new ListNode(new Integer(chars[i]+""));
            n.next = newNode;
            n = newNode;
        }

        return result;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return createListNodeFromNum(getNumOfListNode(l1).add(getNumOfListNode(l2)));
    }

    public static void main(String[] args) {
        N002 n002 = new N002();
        ListNode l1 = n002.createListNodeFromNum(new BigInteger("999999999999999"));
        ListNode l2 = n002.createListNodeFromNum(new BigInteger("99999999999999999999999991"));

        ListNode result = n002.addTwoNumbers(l1, l2);
        System.out.println(n002.getNumOfListNode(result));
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
