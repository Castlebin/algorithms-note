package com.heller.ch03;

import java.util.*;

/**
 * 二叉树的遍历
 * 1. 前序遍历（根、左子树、右子树）    （非递归实现  借助栈）
 * 2. 中序遍历（左子树、根、右子树）    （非递归实现  借助栈）
 * 3. 后序遍历（左子树、右子树、根）    （非递归实现  借助栈）
 */
public class TreeTraversal2 {

    /**
     * 前序遍历（根、左子树、右子树）  （非递归实现  借助栈）
     */
    public static void preOrderTraversal(TreeNode root) {
        TreeNode currentNode = root;
        Stack<TreeNode> stack = new Stack<>();
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {// 第一次遇到元素
                stack.push(currentNode);
                System.out.println(currentNode.data);
                currentNode = currentNode.left;     // 一直往左走，直到没有左儿子了
            }
            if (!stack.isEmpty()) {
                // 第二次遇到元素
                currentNode = stack.pop();
                currentNode = currentNode.right;    // 看看有没有右子树需要处理
            }
        }
    }

    /**
     * 中序遍历（左子树、根、右子树）  （非递归实现  借助栈）
     */
    public static void inOrderTraversal(TreeNode root) {
        TreeNode currentNode = root;
        Stack<TreeNode> stack = new Stack<>();
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {// 第一次遇到元素
                stack.push(currentNode);
                currentNode = currentNode.left;     // 一直往左走，直到没有左儿子了
            }
            if (!stack.isEmpty()) {
                // 第二次遇到元素，打印（处理）
                currentNode = stack.pop();
                System.out.println(currentNode.data);
                currentNode = currentNode.right;    // 看看有没有右子树需要处理
            }
        }
    }

    // https://www.cnblogs.com/SHERO-Vae/p/5800363.html
    /**
     * 后序遍历的非递归实现是三种遍历方式中最难的一种。因为在后序遍历中，
     * 要保证左孩子和右孩子都已被访问并且左孩子在右孩子前访问才能访问根结点，
     * 这就为流程的控制带来了难题。下面介绍两种思路。
     */
    /**
     * 后序遍历（左子树、右子树、根）  （非递归实现  借助栈）
     *
     * 只有后序遍历，每个元素才会完整的有三次遇到
     *
     * * 第一种思路：
     *      *  对于任一结点P，将其入栈，然后沿其左子树一直往下搜索，直到搜索到没有左孩子的结点，
     *      *  此时该结点出现在栈顶，但是此时不能将其出栈并访问， 因此其右孩子还为被访问。
     *      *  所以接下来按照相同的规则对其右子树进行相同的处理，当访问完其右孩子时，该结点又出现在栈顶，
     *      *  此时可以将其出栈并访问。这样就 保证了正确的访问顺序。
     *      *  可以看出，在这个过程中，每个结点都两次出现在栈顶，只有在第二次出现在栈顶时，才能访问它。
     *      *  因此需要多设置一个变量标识该结点是 否是第一次出现在栈顶。
     */
    public static void postOrderTraversal_1(TreeNode root) {
        TreeNode currentNode = root;
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> flagSet = new HashSet<>();
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {// 第一次遇到元素
                stack.push(currentNode);
                currentNode = currentNode.left;     // 一直往左走，直到没有左儿子了
            }
            if (!stack.isEmpty()) {
                currentNode = stack.peek();
                if (!flagSet.contains(currentNode)) { // 第二次遇到，还不能出栈
                    flagSet.add(currentNode);
                    currentNode = currentNode.right;    // 看看有没有右子树需要处理
                } else {
                    currentNode = stack.pop();  // 第三次遇到，可以pop了
                    System.out.println(currentNode.data);
                    currentNode = null;     // ** 注意了！
                }
            }
        }
    }

    /**
     * * 第二种思路：
     *      *  要保证根结点在左孩子和右孩子访问之后才能访问，因此对于任一结点P，先将其入栈。
     *      *  如果P不存在左孩子和右孩子，则可以直接访问它；
     *      *  或者P存 在左孩子或者右孩子，但是其左孩子和右孩子都已被访问过了，则同样可以直接访问该结点。
     *      *  若非上述两种情况，则将P的右孩子和左孩子依次入栈，这样就保证了 每次取栈顶元素的时候，
     *      *  左孩子在右孩子前面被访问，左孩子和右孩子都在根结点前面被访问。
     */
    public static void postOrderTraversal_2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if ((cur.left == null && cur.right == null) //如果当前结点没有孩子结点或者孩子节点都已被访问过
                || (pre != null && (pre == cur.left || pre == cur.right))) {
                System.out.println(cur.data);
                stack.pop();
                pre = cur;
            } else {
                if (cur.right != null) { // 注意，先右后左，因为是栈，需要保证左儿子先出栈
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
    }

}
