package leetcode.N1_N99;

import java.util.ArrayList;
import java.util.List;

import leetcode.base.TreeNode;

/**
 * 各种 树的遍历：
 * 144. 二叉树的前序遍历 https://leetcode.cn/problems/binary-tree-preorder-traversal/
 * 94. 二叉树的中序遍历 https://leetcode.cn/problems/binary-tree-inorder-traversal/
 * 145. 二叉树的后序遍历 https://leetcode.cn/problems/binary-tree-postorder-traversal/
 * 102. 二叉树的层序遍历 https://leetcode.cn/problems/binary-tree-level-order-traversal/
 *
 * 589. N 叉树的前序遍历 https://leetcode.cn/problems/n-ary-tree-preorder-traversal/
 * 590. N 叉树的后序遍历 https://leetcode.cn/problems/n-ary-tree-postorder-traversal/
 * 429. N 叉树的层序遍历 https://leetcode.cn/problems/n-ary-tree-level-order-traversal/
 */

/**
 * 94. 二叉树的中序遍历
 */
public class T94 {

    // 解法 1：递归
    public List<Integer> inorderTraversal_1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traversal(root, res);
        return res;
    }

    private void traversal(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        traversal(root.left, res);
        res.add(root.val);
        traversal(root.right, res);
    }

}