package leetcode.N100_N199;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import leetcode.base.tree.Node;
import leetcode.base.tree.NodeUtil;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 */
public class T117 {

    /**
     * 简单的层序遍历而已
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<Node> nextQueue = new LinkedList<>();
            List<Node> curLevel = new ArrayList<>(queue);
            for (int index = 0; index < curLevel.size(); index++) {
                Node node = curLevel.get(index);
                if (index < curLevel.size() - 1) {
                    node.next = curLevel.get(index + 1);
                }
                if (node.left != null) {
                    nextQueue.add(node.left);
                }
                if (node.right != null) {
                    nextQueue.add(node.right);
                }
            }
            queue = nextQueue;
        }

        return root;
    }

    @Test
    public void test() {
        Node node = NodeUtil.buildTree(new Integer[] {1, 2, 3, 4, 5, null, 7});
        Node connected = connect(node);
        Assert.assertEquals(node, connected);
    }

}
