package ds.ch04;

import ds.ch03.TreeNode;

import java.util.EmptyStackException;

public class BinTree {

    public TreeNode find(int element, TreeNode bst) {
        if (bst == null) {
            return null;
        }
        if (element == bst.data) {
            return bst;
        } else if (element < bst.data) {
            return find(element, bst.left);
        } else {
            return find(element, bst.right);
        }
    }

    public TreeNode findMin(TreeNode bst) {
        if (bst == null || bst.left == null) {
            return bst;
        }
        return findMin(bst.left);
    }

    public TreeNode findMax(TreeNode bst) {
        if (bst == null || bst.right == null) {
            return bst;
        }
        return findMax(bst.right);
    }

    public TreeNode insert(int element, TreeNode bst) {
        if (bst == null) {
            return new TreeNode(element);
        }
        if (element < bst.data) {
            bst.left = insert(element, bst.left);
        } else if (element > bst.data) {
            bst.right = insert(element, bst.right);
        }
        return bst;
    }

    public TreeNode delete(int element, TreeNode bst) {
        if (bst == null) {
            System.out.println("要删除的元素不存在！");
        } else if (element < bst.data) {
            bst.left = delete(element, bst.left);
        } else if (element > bst.data) {
            bst.right = delete(element, bst.right);
        } else {// 找到要删除的元素了
            // 1. 如果要删除的元素不幸有两个子节点
            if (bst.left != null && bst.right != null) {
                TreeNode tmp = findMin(bst.right);
                bst.data = tmp.data;
                bst.right = delete(tmp.data, bst.right);
            } else {
                if (bst.right == null) {
                    bst = bst.left;
                } else {
                    bst = bst.right;
                }
            }
        }
        return bst;
    }

    public TreeNode delete_1(int element, TreeNode bst) {
        if (bst == null
                || (bst.data == element && bst.right == null && bst.left == null)) {
            return null;
        }
        TreeNode cur = bst;
        while (cur != null) {
            TreeNode prev = cur;
            if (element < cur.data) {
                cur = cur.left;
            } else if (element > cur.data) {
                cur = cur.right;
            } else {// 找到要删除的元素了
                if (cur.left != null && cur.right != null) {
                    // 找到右子树的最小元素，替换掉当前节点元素值，再从右边删除那个右子树的最小元素
                    TreeNode tmp = findMin(cur.right);
                    bst.data = tmp.data;

                    // 从右子树中删除那个右子树的最小元素
                    cur = cur.right;
                    element = tmp.data;
                } else {
                    if (bst.right == null) {
                        prev.right = bst.left;
                    } else {
                        prev.right = bst.right;
                    }
                }
            }
        }
        return bst;
    }

}
