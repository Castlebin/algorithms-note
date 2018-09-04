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

    /**
     * 删除的递归实现  （删除变成替换，用 右子树中的最小节点 或者 左子树中的最大节点 替换要删除的节点元素）
     */
    public static TreeNode delete(TreeNode root, Integer element) {
        if (root == null) {
            System.out.println("要删除的节点没找到");
        } else {// 查找要删除的元素
            if (element < root.data) {
                root.left = delete(root.left, element); // 递归的从左子树进行删除
            } else if (element > root.data) {
                root.right = delete(root.right, element); // 递归的从右子树进行删除
            } else {    // 找到了要删除的节点
                if (root.left != null && root.right != null) { // 要删除的节点 有 左子树 也有 右子树
                    TreeNode tmp = findMin(root.right);  // 从右子树中找到最小节点
                    root.data = tmp.data;   // 用右子树中的最小节点值替换要删除节点的值
                    root.right = delete(root.right, tmp.data);  // 递归的删除 那个值 被用来作为替换的那个节点
                } else {    // 要删除的那个节点为叶节点或者只有一个儿子的情况
                    if (root.left == null) {    // 有右儿子或者无子节点的情况
                        root = root.right;
                    } else {    // 有左儿子的情况
                        root = root.left;
                    }

                    // 上面的code block等价于
                    /*
                     if (root.left == null && root.right == null) {// 要删除的为叶节点
                        root = null;
                     } else if( root.right != null) {// 要删除的节点只有右儿子
                        root = root.right;
                     } else {// 要删除的节点只有左儿子
                        root = root.left;
                     }
                     */
                }
            }
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

    public static TreeNode delete_1(TreeNode root, Integer element) {
        TreeNode cur = root;
        while (cur != null) {
            if (element < cur.data) {
                cur = cur.left;
            } else if (element > cur.data) {
                cur = cur.right;
            } else {// 找到了要删除的元素
                if (cur.left != null && cur.right != null) {// 要删除的元素有左儿子也有右儿子
                    TreeNode tmp = findMin(root.right); // 从右子树中找到最小节点
                    cur.data = tmp.data;    // 用右子树中的最小节点值替换要删除节点的值

                    cur = tmp;  // 替换之后，变为从右子树中去删除tmp节点的值
                    element = tmp.data;
                } else { // 要删除的那个节点为叶节点或者只有一个儿子的情况
                    if (root.left == null) {    // 有右儿子或者无子节点的情况
                        root = root.right;
                    } else {    // 有左儿子的情况
                        root = root.left;
                    }

                    // 上面的code block等价于
                    /*
                     if (root.left == null && root.right == null) {// 要删除的为叶节点
                        root = null;
                     } else if( root.right != null) {// 要删除的节点只有右儿子
                        root = root.right;
                     } else {// 要删除的节点只有左儿子
                        root = root.left;
                     }
                     */
                }
            }
        }

        return root;
    }

}
