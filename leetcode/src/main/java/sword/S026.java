package sword;

import org.junit.Test;
import common.TreeNode;

import java.util.Stack;

/**
 剑指 Offer 26. 树的子结构
 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

 B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 */
public class S026 {

    /**
     * 此解答时间慢了，也比较复杂
     */
    public boolean isSubStructure(TreeNode a, TreeNode b) {
        if (b == null || a == null) {
            return false;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = a;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                if (checkSubStructure(cur, b)) {
                    return true;
                }
                cur = cur.right;
            }
        }
        return false;
    }

    private boolean checkSubStructure(TreeNode a, TreeNode b) {
        if (a != null && b != null && a.val != b.val) {
            return false;
        }

        Stack<TreeNode> stackA = new Stack<>();
        Stack<TreeNode> stackB = new Stack<>();
        while ((a != null || !stackA.isEmpty())
                && (b != null || !stackB.isEmpty())) {
            while (a != null && b != null) {
                stackA.push(a);
                stackB.push(b);
                if (a.val != b.val) {
                    return false;
                }
                a = a.left;
                b = b.left;
            }
            if (b != null && a == null) {
                return false;
            }
            if (!stackA.isEmpty() && !stackB.isEmpty()) {
                a = stackA.pop();
                b = stackB.pop();
                a = a.right;
                b = b.right;
            }
        }

        return b == null;
    }


    /**
     * 双100 解答，递归，妙啊
     */
    class Solution {
        public boolean isSubStructure(TreeNode A, TreeNode B) {

            // 如果A或B都走完了还没找到，那应该就是找不到了
            if(A == null || B == null) {
                return false;
            }

            //看看当前节点成不？不成就看看左边，不然看看右边？
            return checkSubStructure(A,B)
                    || isSubStructure(A.left, B)
                    || isSubStructure(A.right, B);
        }


        boolean checkSubStructure(TreeNode a, TreeNode b){
            // b这边都看完了，还没挑出不同？那就是了吧！
            if(b == null) {
                return true;
            }
                // b这边还没看完了，a那边就null了？
            else if(a == null) {
                return false;
            }

            return a.val == b.val
                    && checkSubStructure(a.left, b.left)
                    && checkSubStructure(a.right, b.right);
        }

    }

    @Test
    public void test() {

    }

}
