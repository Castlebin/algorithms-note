package sword;


/**
 * 08. 给定一颗二叉树 其中的一个节点 对象（指针），找出二叉树中序遍历该节点的下一个遍历节点
 * <p>
 * 注意：这里的二叉树节点，除了有左右儿子外，还有一个指向父节点的指针
 *
 * https://www.nowcoder.com/practice/9023a0c988684a53960365b889ceaf5e?tpId=13&&tqId=11210&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class S008 {

    public class Solution {
        public TreeLinkNode GetNext(TreeLinkNode pNode) {
            // 本题需要画出一颗二叉树的图，通过分析，得到找二叉树中序遍历中指定节点的下一个节点的规律，分情况解决即可
            /** 共有三种情况
             1. 该节点有右子树，那么下一个节点，为它的右子树的最左节点
             2. 该节点无右子树
               2.1 该节点是它父节点的左子树，那么 下一个节点，就是它的父节点
               2.2 一直往上走，直到发现一个节点是它父节点的左子树，那么，该节点的父节点就是下一个节点
             3. 其他，返回 null
             */
            if (pNode == null) {
                return null;
            }
            if (pNode.right != null) {
                return findMostLeft(pNode.right);
            } else {
                TreeLinkNode cur = pNode;
                TreeLinkNode parent = cur.next;
                while (parent != null) {
                    if (parent.left == cur) {
                        return parent;
                    }
                    cur = parent;
                    parent = cur.next;
                }
            }

            return null;
        }

        private TreeLinkNode findMostLeft(TreeLinkNode pNode) {
            while (pNode != null && pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }

        public class TreeLinkNode {
            int val;
            TreeLinkNode left = null;
            TreeLinkNode right = null;

            // 指向父节点的指针（叫parent 更好，牛客网命名太垃圾了，方法名也是）
            TreeLinkNode next = null;

            TreeLinkNode(int val) {
                this.val = val;
            }
        }
    }

}
