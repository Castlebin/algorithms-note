package sword;

import org.junit.Assert;
import org.junit.Test;
import common.TreeNode;

import java.util.Stack;

/**
 剑指 Offer 28. 对称的二叉树

 请实现一个函数，用来判断一棵二叉树是不是对称的。
 如果一棵二叉树和它的镜像一样，那么它是对称的。
 */
public class S028 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        Stack<TreeNode> stackLeft = new Stack<>();
        Stack<TreeNode> stackRight = new Stack<>();
        TreeNode curLeft = null, curRight = null;
        while ((left != null || !stackLeft.isEmpty())
            && (right != null || !stackRight.isEmpty())) {
            while (left != null) {
                stackLeft.push(left);
                left = left.left;
            }
            while (right != null) {
                stackRight.push(right);
                right = right.right;
            }

            if (stackLeft.size() != stackRight.size()) {
                return false;
            }

            if (!stackLeft.isEmpty()) {
                curLeft = stackLeft.pop();
                left = curLeft.right;
            }
            if (!stackRight.isEmpty()) {
                curRight = stackRight.pop();
                right = curRight.left;
            }
            if (curLeft.val != curRight.val) {
                return false;
            }
        }
        return stackLeft.isEmpty() && stackRight.isEmpty()
                && left == null && right == null;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1){{
            left = new TreeNode(2){{
                left = new TreeNode(2);
            }};
            right = new TreeNode(2){{
                left = new TreeNode(2);
            }};
        }};
        Assert.assertEquals(false, isSymmetric(root));
    }

}
