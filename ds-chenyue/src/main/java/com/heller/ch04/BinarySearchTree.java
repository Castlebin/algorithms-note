package com.heller.ch04;

import com.heller.ch03.TreeNode;

import java.lang.reflect.WildcardType;

/**
 * 实现二叉搜索树的几种基本操作
 *
 * find、findMin、findMax、insert、delete
 *
 * delete操作稍微复杂一点点，因为删除必须要考虑，删除后，要仍然是一颗二叉搜索树
 */
public class BinarySearchTree {

    /**
     * find 递归、非递归
     */
    public static TreeNode find(TreeNode bst, int element) {
        if (bst == null) {
            return null;
        }
        if (element > bst.data) {
            return find(bst.right, element);
        } else if (element < bst.data) {
            return find(bst.left, element);
        } else {
            return bst;
        }
    }

    public static TreeNode find_1(TreeNode bst, int element) {
        while (bst != null) {
            if (element > bst.data) {
                bst = bst.right;
            } else if (element < bst.data) {
                bst = bst.left;
            } else {
                return bst;
            }
        }
        return null;
    }

    /**
     * findMin 递归、非递归       (最左边的节点)
     */
    public static TreeNode findMin(TreeNode bst) {
        if (bst != null && bst.left != null) {
            return findMin(bst.left);
        }
        return bst;
    }

    public static TreeNode findMin_1(TreeNode bst) {
        while (bst != null && bst.left != null) {
            bst = bst.left;
        }
        return bst;
    }

    /**
     * findMax 递归、非递归       (最右边的节点)
     */
    public static TreeNode findMax(TreeNode bst) {
        if (bst != null && bst.right != null) {
            return findMax(bst);
        }
        return bst;
    }

    public static TreeNode findMax_1(TreeNode bst) {
        while (bst != null && bst.right != null) {
            bst = bst.right;
        }
        return bst;
    }

    /**
     * inset 递归实现、非递归
     */
    public static TreeNode insert(TreeNode bst, int element) {

        return null;
    }

    public static TreeNode insert_1(TreeNode bst, int element) {

        return null;
    }

    /**
     * 删除 变 替换
     *
     * 删除的递归实现  （删除变成替换，用 右子树中的最小节点 或者 左子树中的最大节点 替换要删除的节点元素）
     */
    public static TreeNode delete(TreeNode bst, int element) {

        return null;
    }

    public static TreeNode delete_1(TreeNode bst, int element) {

        return null;
    }

}
