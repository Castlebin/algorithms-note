package leetcode.base;

/**
 * 单向链表（节点）
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    /**
     * 按 leetcode 的测试用例描述，构建测试用的单项链表
     *
     * @param data 链表节点数据
     * @param tailNextPos 链表的最后一个节点的 next 指针 指向位置，
     * -1 表示为 null （链表无环），
     * 为 t （t >= 0 && t < data.length ）时 ，表示指向链表的第 t - 1 个节点
     */
    public static ListNode buildLinkedList(int[] data, int tailNextPos) {
        if (data == null || data.length == 0) {
            return null;
        }
        if (tailNextPos >= data.length || tailNextPos < -1) {
            throw new IllegalArgumentException("取值范围超限!");
        }
        // 需要搞个变量，记录尾指针指向的节点（tailNextPos）
        ListNode tailNextNode = null;
        // 为了程序的简单，定义一个无意义节点作为头部 (常用技巧)
        ListNode preHead = new ListNode(-1);
        // 使用 data 数组，构建链表
        ListNode cur = preHead;
        for (int index = 0; index < data.length; index++) {
            cur.next = new ListNode(data[index]);
            cur = cur.next;
            // 记录下来尾指针指向的节点，便于最后处理尾指针
            if (tailNextPos == index) {
                tailNextNode = cur;
            }
        }
        // 处理下尾指针
        cur.next = tailNextNode;

        // head 节点
        ListNode head = preHead.next;
        preHead.next = null; // 回收掉 preHead 节点的内存
        return head;
    }

}
