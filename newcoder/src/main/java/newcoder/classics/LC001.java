package newcoder.classics;

import newcoder.classics.common.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 LC1 	二叉树的最小深度

 求给定二叉树的最小深度。最小深度是指树的根结点到最近叶子结点的最短路径上结点的数量。


 本题的定义和测试用例有不确定的地方，结合测试用例来解
 */
public class LC001 {

    public class Solution {
        /**
         *
         * @param root TreeNode类
         * @return int整型
         */
        public int run (TreeNode root) {
            if (root == null) {
                return 0;
            } else if (root.right == null) {
                return run(root.left) + 1;
            } else if (root.left == null) {
                return run(root.right) + 1;
            }
            return Math.min(run(root.left), run(root.right)) + 1;
        }
    }

    @Test
    public void testLC001Basic() {
        Solution solution = new Solution();
        Assert.assertTrue(0 == solution.run(null));
        Assert.assertTrue(1 == solution.run(new TreeNode(1)));

        TreeNode root = new TreeNode(0);
        TreeNode left = new TreeNode(1);
        root.left = left;
        Assert.assertTrue(2 == solution.run(root));
    }

}
