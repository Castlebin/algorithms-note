package sword;

import org.junit.Assert;
import org.junit.Test;
import sword.common.TreeNode;

import java.util.LinkedList;

/**
 剑指 Offer 68 - II. 二叉树的最近公共祖先
 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

 最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大
 （一个节点也可以是它自己的祖先）。”

 说明:

 所有节点的值都是唯一的。
 p、q 为不同节点且均存在于给定的二叉树中。
*/
public class S068_2 {

    /**
     * 解法 1. 递归 有点慢 一秒多
     */
    class Solution1 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == p && root == q) {
                return root;
            }
            if (root == p) {
                if (root != null) {
                    if (find(root.left, q) == q) {
                        return root;
                    }
                    if (find(root.right, q) == q) {
                        return root;
                    }
                }
            }
            if (root == q) {
                if (root != null) {
                    if (find(root.left, p) == p) {
                        return root;
                    }
                    if (find(root.right, p) == p) {
                        return root;
                    }
                }
            }
            if (root != null) {
                if (find(root.left, p) == p) {
                    if (find(root.right, q) == q) {
                        return root;
                    } else {
                        return lowestCommonAncestor(root.left, p, q);
                    }
                }
                if (find(root.right, p) == p) {
                    if (find(root.left, q) == q) {
                        return root;
                    } else {
                        return lowestCommonAncestor(root.right, p, q);
                    }
                }
            }

            return null;
        }

        public TreeNode find(TreeNode root, TreeNode node) {
            if (root == node) {
                return node;
            }

            if (root != null) {
                if (find(root.left, node) == node) {
                    return node;
                }
                if (find(root.right, node) == node) {
                    return node;
                }
            }

            return null;
        }
    }

    /**
     * todo 未完成
     */
    class Solution2 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == p && root == q) {
                return root;
            }

            LinkedList<TreeNode> pPath = new LinkedList<>();
            boolean pExist = dfsForPath(root, p, pPath);
            if (pExist) {
                LinkedList<TreeNode> qPath = new LinkedList<>();
                boolean qExist = dfsForPath(root, q, qPath);
                if (qExist) {
                    int shortPathLen = Math.min(pPath.size() ,qPath.size());
                    TreeNode ancestor = pPath.getFirst();
                    for (int i = 1; i < shortPathLen; i++) {
                        if (pPath.get(i) != qPath.get(i)) {
                            break;
                        }
                        ancestor = pPath.get(i);
                    }
                    return ancestor;
                }
            }

            return null;
        }

        public boolean dfsForPath(TreeNode root, TreeNode node, LinkedList<TreeNode> path) {
            if (root != null) {
                path.add(root);
                if (root == node) {
                    return true;
                }
                if (dfsForPath(root.left, node, path)) {
                    return true;
                }
                if (path.size() > 1) {
                    path.removeLast();
                }
                if (dfsForPath(root.right, node, path)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Test
    public void test() {
        TreeNode root = TreeNode.buildBinaryTreeFromArray(new Integer[]{3,5,1,6,2,0,8,null,null,7,4});
        TreeNode p = TreeNode.findTreeNodeByValueRecursively(root, 5);
        TreeNode q = TreeNode.findTreeNodeByValueRecursively(root, 1);
        TreeNode target = TreeNode.findTreeNodeByValueRecursively(root, 3);
        Assert.assertEquals(target, new Solution1().lowestCommonAncestor(root, p, q));
    }

}
