package sword.sa;

import leetcode.base.tree.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 剑指 Offer 36. 二叉搜索树与双向链表
 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */
public class T036 {

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        List<Node> res = traversal(root);
        res.get(0).left = res.get(1);
        res.get(1).right = res.get(0);
        return res.get(0);
    }

    List<Node> traversal(Node root) {
        if (root == null) {
            return null;
        }
        Node dummy = new Node();
        Node cur = dummy;

        Node left = root.left;
        Node right = root.right;

        List<Node> leftResult = traversal(left);
        if (leftResult != null) {
            dummy.right = leftResult.get(0);
            leftResult.get(0).left = dummy;
            cur = leftResult.get(1);
        }
        cur.right = root;
        root.left = cur;
        cur = root;

        List<Node> rightResult = traversal(right);
        if (rightResult != null) {
            cur.right = rightResult.get(0);
            rightResult.get(0).left = cur;
            cur = rightResult.get(1);
        }
        List<Node> res = new ArrayList<>();
        res.add(dummy.right);
        res.add(cur);
        return res;
    }

    @Test
    public void test() {
        Node root = buildTree(new Integer[]{4, 2, 5, 1, 3});
        Node node = treeToDoublyList(root);
        System.out.println(node);
    }

    public static Node buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Node root = new Node(arr[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (index < arr.length) {
                Integer left = arr[index++];
                if (left != null) {
                    node.left = new Node(left);
                    queue.add(node.left);
                }
            }
            if (index < arr.length) {
                Integer right = arr[index++];
                if (right != null) {
                    node.right = new Node(right);
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

}
