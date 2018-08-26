package com.heller.ch03;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

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

    /**
     * 后序遍历（左子树、右子树、根）  （非递归实现  借助栈）
     */
    public static void postOrderTraversal(TreeNode root) {

    }

}
