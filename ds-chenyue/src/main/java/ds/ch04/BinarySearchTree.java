package ds.ch04;

import ds.ch03.TreeNode;

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
        if (bst == null) {
            return new TreeNode(element);
        }
        if (element > bst.data) {
            bst.right = insert(bst.right, element);
        } else if (element < bst.data) {
            bst.left = insert(bst.left, element);
        }
        return bst;
    }

    public static TreeNode insert_1(TreeNode bst, int element) {
        if (bst == null) {
            return new TreeNode(element);
        }
        TreeNode cur = bst;
        while (cur != null && cur.data != element) {
            TreeNode prev = cur;
            if (element > cur.data) {
                cur = cur.right;
                if (cur == null) {
                    prev.right = new TreeNode(element);
                }
            } else {
                cur = cur.left;
                if (cur == null) {
                    prev.left = new TreeNode(element);
                }
            }
        }

        return bst;
    }

    /**
     * 删除 变 替换
     *
     * 删除的递归实现  （删除变成替换，用 右子树中的最小节点 或者 左子树中的最大节点 替换要删除的节点元素）
     */
    public static TreeNode delete(TreeNode bst, int element) {
        if (bst == null) {
            System.out.println("要删除的的元素不存在");
        } else if (element > bst.data) {
            bst.right = delete(bst.right, element);
        } else if (element < bst.data) {
            bst.left = delete(bst.left, element);
        } else { // 找到要删除的元素
            if (bst.left != null && bst.right != null) {
                // 找到右子树的最小元素，替换掉当前节点元素值，再从右边递归的删除那个右子树的最小元素
                TreeNode tmp = findMin(bst.right);
                bst.data = tmp.data;
                bst.right = delete(bst.right, tmp.data);
            } else {
                if (bst.left == null) {
                    bst = bst.right;
                } else {
                    bst = bst.left;
                }
            }
        }
        return bst;
    }

    public static TreeNode delete_1(TreeNode bst, int element) {
        if (bst == null || (bst.data == element && bst.right == null && bst.left == null)) {
            return null;
        }
        TreeNode cur = bst;
        while (cur != null) {
            TreeNode prev = cur;
            if (element > cur.data) {
                cur = cur.right;
            } else if (element < cur.data) {
                cur = cur.left;
            } else { // 找到要删除的元素
                if (cur.left != null && cur.right != null) {
                    // 找到右子树的最小元素，替换掉当前节点元素值，再从右边删除那个右子树的最小元素
                    TreeNode tmp = findMin(bst.right);
                    bst.data = tmp.data;

                    // 从右边删除那个右子树的最小元素
                    cur = cur.right;
                    element = tmp.data;
                } else {
                    if (bst.left == null) {
                        prev.right = bst.right;
                    } else {
                        prev.right = bst.left;
                    }
                }
            }
        }
        return bst;
    }

}
