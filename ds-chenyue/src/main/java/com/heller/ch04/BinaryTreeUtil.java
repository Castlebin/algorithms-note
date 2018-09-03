package com.heller.ch04;

import com.heller.ch03.TreeNode;

/**
 * 实现二叉搜索树的几种基本操作
 *
 * find、findMin、findMax、insert、delete
 *
 * delete操作稍微复杂一点点，因为删除必须要考虑，删除后，要仍然是一颗二叉搜索树
 */
public class BinaryTreeUtil {

    /**
     * find 递归实现
     */
    public static TreeNode find(TreeNode root, Integer element) {
        if (root == null) {
            return null;
        }
        if (element < root.data) {// 节点元素比要查找的元素大，往左子树搜索
            return find(root.left, element);
        } else if (element > root.data) {// 节点元素比要查找的元素小，往右子树搜索
            return find(root.right, element);
        } else {// 相等，返回
            return root;
        }
    }

    /**
     * findMin 递归实现
     */
    public static TreeNode findMin(TreeNode root) {
        if (root != null && root.left != null) {
            return findMin(root.left);
        }
        return root;
    }

    /**
     * findMax 递归实现
     */
    public static TreeNode findMax(TreeNode root) {
        if (root != null && root.right != null) {
            return findMax(root.right);
        }
        return root;
    }

    /**
     * inset 递归实现 (考虑原树为null的情况，所以需要返回)
     */
    public static TreeNode insert(TreeNode root, Integer element) {
        if (root == null) {
            return new TreeNode(element);
        }
        if (element < root.data) {
            root.left = insert(root.left, element);
        } else if (element > root.data) {
            root.right = insert(root.right, element);
        }
        return root;
    }

    // ====================== 非递归实现 ======================
    /**
     * find、findMin、findMax、insert 的非递归实现
     */
    public static TreeNode find_1(TreeNode root, Integer element) {
        while (root != null) {
            if (element > root.data) {
                root = root.right;
            } else if (element < root.data) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }

    public static TreeNode findMin_1(TreeNode root) {
        while (root != null && root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static TreeNode findMax_1(TreeNode root) {
        while (root != null && root.right != null) {
            root = root.right;
        }
        return root;
    }

    public static TreeNode insert_1(TreeNode root, Integer element) {
        if (root == null) {
            return new TreeNode(element);
        }

        TreeNode prev;
        TreeNode cur = root;
        while (cur != null) {
            prev = cur;
            if (element < cur.data) {
                cur = cur.left;
                if (cur == null) {
                    prev.left = new TreeNode(element);
                }
            } else if (element > cur.data) {
                cur = cur.right;
                if (cur == null) {
                    prev.right = new TreeNode(element);
                }
            } else {
                return root;
            }
        }

        return root;
    }

}
