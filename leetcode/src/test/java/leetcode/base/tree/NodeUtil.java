package leetcode.base.tree;

import java.util.LinkedList;
import java.util.Queue;

public class NodeUtil {

    /**
     * 通过数组构建二叉树，数组中的元素按层序遍历的顺序存储，null 表示空节点
     * leetcode 中常用的二叉树测试数据构建二叉树的方法
     */
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
