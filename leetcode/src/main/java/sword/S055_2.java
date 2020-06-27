package sword;

import org.junit.Test;

/**
 剑指 Offer 55 - II. 平衡二叉树

 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
 如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
*/
public class S055_2 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        boolean isBalanced = (leftDepth - rightDepth <= 1) && (rightDepth - leftDepth <= 1);

        return isBalanced
                && isBalanced(root.left) && isBalanced(root.right);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.right), maxDepth(root.left)) + 1;
    }

    @Test
    public void test() {
    }

}
