package algorithm;

import common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class N0103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int level = 0;
        // 记录，并且在合适的地方更新 下一个层的第一个节点是谁
        TreeNode nextLevelFirstNode = root;
        LinkedList<Integer> levelVals = new LinkedList<>();
        while (!deque.isEmpty()) {
            TreeNode cur = deque.removeFirst();
            if (cur == nextLevelFirstNode) {
                level++;
                levelVals = new LinkedList<>();
                levelVals.add(cur.val);
                ans.add(levelVals);
                if (cur.left != null) {
                    nextLevelFirstNode = cur.left;
                } else if (cur.right != null) {
                    nextLevelFirstNode = cur.right;
                } else {
                    // 进入到这一个分支，说明有两种情况
                    // 1. 当前层已经完结   （新的一行，第一个节点，没有子节点，也没有兄弟节点，说明递归可以结束了）

                    // 2. 当前层还没遍历完，也就是说当前节点还有兄弟，搜索下一个可能的下层第一个节点
                    // 当前队列里的元素都是自己同一层的，所以，可以搜索下一个层的第一个节点
                    // 关键点就在这里
                    if (!deque.isEmpty()) {
                        for (TreeNode treeNode : deque) {
                            if (treeNode.left != null) {
                                nextLevelFirstNode = treeNode.left;
                                break;
                            } else if (treeNode.right != null) {
                                nextLevelFirstNode = treeNode.right;
                                break;
                            }
                        }
                    }
                }
            } else {
                if (level % 2 == 1) {
                    levelVals.add(cur.val);
                } else {
                    levelVals.addFirst(cur.val);
                }
            }

            if (cur.left != null) {
                deque.add(cur.left);
            }
            if (cur.right != null) {
                deque.add(cur.right);
            }
        }
        return ans;
    }

    @Test
    public void test() {
        Integer[] arr = new Integer[] {3,9,20,null,null,15,7};
        TreeNode root = TreeNode.buildBinaryTreeFromArray(arr);
        zigzagLevelOrder(root);
    }

}
