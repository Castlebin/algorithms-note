package leetcode.N100_N199;

import java.util.LinkedList;
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
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                Node node = queue.poll();
                if (index < size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
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
