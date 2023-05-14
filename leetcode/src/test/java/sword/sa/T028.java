package sword.sa;

import org.junit.Assert;
import org.junit.Test;

import common.TreeUtil;
import leetcode.base.TreeNode;

/**
 * 剑指 Offer 28. 对称的二叉树    (简单题不简单！！！)
 *
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 */
public class T028 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        // 重点步骤！！！
        if (left != null && right != null) {
            return left.val == right.val
                    && isSymmetric(left.left, right.right)
                    && isSymmetric(left.right, right.left);
        }
        return false;
    }

    @Test
    public void test() {
        TreeNode tree1 = TreeUtil.buildTree(new Integer[] {1, 2, 2, 3, 4, 4, 3});
        Assert.assertEquals(true, isSymmetric(tree1));

        TreeNode tree2 = TreeUtil.buildTree(new Integer[] {1, 2, 2, null, 3, null, 3});
        Assert.assertEquals(false, isSymmetric(tree2));
    }

}
