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

    private AVLTreeNode delete(AVLTreeNode avl, Integer data) {
        if (avl == null) {
            return null;
        }
        if (data < avl.data) {
            avl = delete(avl.left, data);
        } else if (data > avl.data) {
            avl = delete(avl.right, data);
        } else {
            // 找到了要删除的节点值 avl
            if (avl.left != null && avl.right != null) {
                // 找到右子树的最小元素，替换掉当前节点元素值，再从右边递归的删除那个右子树的最小元素
                AVLTreeNode tmp = findMin(avl.right);
                avl.data = tmp.data;
                avl.right = delete(avl.right, tmp.data);
            } else if (avl.left == null) {
                avl = avl.right;
            } else {
                avl = avl.left;
            }
        }

        // 可能需要调整节点，做旋转（写了一个通用的旋转方法，包含了 4 种旋转）
        // 这样，相对于 普通的二叉搜索树 的 删除操作，这是这里多了一个调整平衡 的步骤而已
        // 插入 操作的代码，其实也可以用这个进行简化
        avl = doRotation(avl);

        return avl;
    }

    /**
     * 通用的 rotation 方法，4 种 旋转都在这里实现，可以简化其他的代码
     *
     * 相当好 ！！！ 提炼出了 旋转的 4种 情况 的判断方法
     */
    private AVLTreeNode doRotation(AVLTreeNode avl) {
        if (avl == null) {
            return null;
        }
        if (getHeight(avl.left) - getHeight(avl.right) == 2) {
            // 需要 左单旋
            if (getHeight(avl.left.left) >= getHeight(avl.left.right)) {
                return singleLeftRotation(avl);
            } else {
                // 需要做 左-右 双旋
                return doubleLeftRightRotation(avl);
            }
        } else if (getHeight(avl.right) - getHeight(avl.left) == 2) {
            if (getHeight(avl.right.right) >= getHeight(avl.right.left)) {
                return singleRightRotation(avl);
            } else {
                return doubleRightLeftRotation(avl);
            }
        }

        return avl;
    }

    public AVLTreeNode findMin(AVLTreeNode avl) {
        if (avl != null && avl.left != null) {
            return findMin(avl.left);
        }
        return avl;
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
