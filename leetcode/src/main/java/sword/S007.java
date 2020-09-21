package sword;

import org.junit.Test;
import common.TreeNode;

/**
 * 07. 重建二叉树
 * <p>
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
 * <p>
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 */
public class S007 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null
                || preorder.length == 0 || inorder.length == 0
        ) {
            return null;
        }

        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, preorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder,
                               int preorderStart, int preorderEnd,
                               int inorderStart, int inorderEnd) {
        if (preorderStart > preorderEnd) {
            return null;
        } else if (preorderStart == preorderEnd) {
            return new TreeNode(preorder[preorderStart]);
        } else {
            // 前序遍历的第一个元素，是子树的根节点
            int pValue = preorder[preorderStart];
            TreeNode parent = new TreeNode(pValue);
            // 找到子树根节点在中序遍历数组中的位置，从而将中序遍历的结果分为左右两个子树
            int pi = findParentIndexFromInorder(inorder, pValue, inorderStart, inorderEnd);
            // 关键在于，注意 左右子树的起止位置，前序、中序，都需要计算好
            // 左子树
            parent.left = buildTree(preorder, inorder,
                    preorderStart + 1, preorderStart + pi - inorderStart,
                    inorderStart, pi - 1);
            // 右子树
            parent.right = buildTree(preorder, inorder,
                    preorderStart + pi - inorderStart + 1, preorderEnd,
                    pi + 1, inorderEnd);
            return parent;
        }
    }

    private int findParentIndexFromInorder(int[] inorder, int pValue, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == pValue) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[] preorder = new int[]{3,2,1};
        int[] inorder = new int[]{1,2,3};
        TreeNode tree = buildTree(preorder, inorder);
    }

}

