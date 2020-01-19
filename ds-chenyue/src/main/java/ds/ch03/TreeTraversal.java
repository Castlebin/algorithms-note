package ds.ch03;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 二叉树的遍历
 * 1. 前序遍历（根、左子树、右子树）               （递归调用）
 * 2. 中序遍历（左子树、根、右子树）               （递归调用）
 * 3. 后序遍历（左子树、右子树、根）               （递归调用）
 * 4. 层次遍历（从上外下、从左往右、一层一层来）    （借助队列实现）
 */
public class TreeTraversal {

    /**
     * 前序遍历（根、左子树、右子树）               （递归调用）
     */
    public static void preOrderTraversal(TreeNode root) {
        if (root != null) {
            System.out.println(root.data);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    /**
     * 中序遍历（左子树、根、右子树）               （递归调用）
     */
    public static void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.println(root.data);
            inOrderTraversal(root.right);
        }
    }

    /**
     * 后序遍历（左子树、右子树、根）               （递归调用）
     */
    public static void postOrderTraversal(TreeNode root) {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.println(root.data);
        }
    }

    /**
     * 层次遍历（从上外下、从左往右、一层一层来）    （借助队列实现）
     */
    public static void levelOrderTraveral(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode currentNode = nodeQueue.remove();
            System.out.println(currentNode.data);
            if (currentNode.left != null) {
                nodeQueue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                nodeQueue.add(currentNode.right);
            }
        }
    }

}
