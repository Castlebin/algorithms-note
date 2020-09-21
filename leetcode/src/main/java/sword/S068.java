package sword;

import org.junit.Test;
import common.TreeNode;

/**
 面试题68 - I. 二叉搜索树的最近公共祖先

 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

 最近公共祖先的定义为：
 “ 对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 满足 x 是 p、q 的祖先且 x 的深度尽可能大
 （一个节点也可以是它自己的祖先）。”
*/
public class S068 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || (root == p && root == q)
                || (root.val >= p.val && root.val <= q.val)
                || (root.val <= p.val && root.val >= q.val)) {
            return root;
        } else if (root.val <= p.val && root.val <= q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return lowestCommonAncestor(root.left, p, q);
        }
    }

    @Test
    public void test() {

    }

}
