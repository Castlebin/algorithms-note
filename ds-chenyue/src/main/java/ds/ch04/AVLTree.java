package ds.ch04;

public class AVLTree {

    class AVLTreeNode {
        Integer data;
        AVLTreeNode left;
        AVLTreeNode right;

        AVLTreeNode(Integer data) {
            this.data = data;
        }
    }

    public AVLTreeNode insert(AVLTreeNode t, Integer data) {
        if (t == null) {
            t = new AVLTreeNode(data);
        } else if (data < t.data) {// 插入t的左子树
            t.left = insert(t.left, data);
            /** 如果需要左旋 */
            if (getHeight(t.left) - getHeight(t.right) == 2) {
                if (data < t.left.data) {
                    t = singleLeftRotation(t);/* 左单旋 */
                } else {
                    t = doubleLeftRightRotation(t);/* 左-右 双旋 */
                }
            }
        } else if (data > t.data) {
            t.right = insert(t.right, data);
            /**如果需要右旋*/
            if (getHeight(t.right) - getHeight(t.left) == 2) {
                if (data > t.right.data) {
                    t = singleRightRotation(t); /* 右单旋 */
                } else {
                    t = doubleRightLeftRotation(t);/* 右-左 双旋 */
                }
            }
        }

        return t;
    }

    private AVLTreeNode singleLeftRotation(AVLTreeNode a) { /* 注意：A必须有一个左子结点B */
        /* 将A与B做左单旋，更新A与B的高度，返回新的根结点B */
        AVLTreeNode b = a.left;
        a.left = b.right;
        b.right = a;
        return b;
    }

    private AVLTreeNode singleRightRotation(AVLTreeNode a) {
        AVLTreeNode b = a.right;
        a.right = b.left;
        b.left = a;
        return b;
    }

    private AVLTreeNode doubleLeftRightRotation(AVLTreeNode a) {
        /* 注意：A必须有一个左子结点B，且B必须有一个右子结点C */
        /* 将A、B与C做两次单旋，返回新的根结点C */

        /* 将B与C做右单旋，C被返回 */
        a.left = singleRightRotation(a.left);
        /* 将A与C做左单旋，C被返回 */
        return singleLeftRotation(a);
    }

    private AVLTreeNode doubleRightLeftRotation(AVLTreeNode a) {
        a.right = singleLeftRotation(a.right);
        return singleRightRotation(a);
    }

    private int getHeight(AVLTreeNode t) {
        return getHeightPreOrder(t);
    }

    private int getHeightPreOrder(AVLTreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        return max(getHeightPreOrder(treeNode.left), getHeightPreOrder(treeNode.right)) + 1;
    }

    private int max(int a, int b) {
        return a >= b ? a : b;
    }

}
