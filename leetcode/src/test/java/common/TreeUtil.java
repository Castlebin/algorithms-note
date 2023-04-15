package common;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.base.TreeNode;

public class TreeUtil {

    /**
     * 通过数组构建二叉树，数组中的元素按层序遍历的顺序存储，null 表示空节点
     * leetcode 中常用的二叉树测试数据构建二叉树的方法
     */
    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (index < arr.length) {
                Integer left = arr[index++];
                if (left != null) {
                    node.left = new TreeNode(left);
                    queue.add(node.left);
                }
            }
            if (index < arr.length) {
                Integer right = arr[index++];
                if (right != null) {
                    node.right = new TreeNode(right);
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

}
