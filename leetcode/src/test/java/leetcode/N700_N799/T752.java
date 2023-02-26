package leetcode.N700_N799;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * 752. Open the Lock
 * 752. 打开转盘锁
 */
public class T752 {

    /**
     * 本质是一个求 最短路径长度 的问题  ， 所以用 BFS
     */
    public int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        String init = buildInit(target);
        if (visited.contains(init)) {
            return -1;
        }

        Set<String> q1 = new HashSet<>();
        q1.add(init);
        Set<String> q2 = new HashSet<>();
        q2.add(target);

        int step = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            // 使用小的那一个集合，理论上能加快迭代速度（因为小的集合，意味着它的下一层节点也较少）
            if (q1.size() > q2.size()) {
                Set<String> temp = q1;
                q1 = q2;
                q2 = temp;
            }
            Set<String> nextLevel = new HashSet<>();
            for (String node : q1) {
                // 两端分别迭代（双向迭代），现在已经相遇！
                if (q2.contains(node)) {
                    return step;
                }
                visited.add(node);
                for (int i = 0; i < target.length(); i++) {
                    String down = down(node, i);
                    if (!visited.contains(down)) {
                        nextLevel.add(down);
                    }
                    String up = up(node, i);
                    if (!visited.contains(up)) {
                        nextLevel.add(up);
                    }
                }
            }
            step++;

            // 换一下迭代方向，达到双向迭代的效果
            q1 = q2;
            q2 = nextLevel;
        }
        return -1;
    }

    private String buildInit(String target) {
        char[] chars = new char[target.length()];
        Arrays.fill(chars, '0');
        return new String(chars);
    }

    private String down(String s, int t) {
        char[] chars = s.toCharArray();
        if (chars[t] == '0') {
            chars[t] = '9';
        } else {
            chars[t] -= 1;
        }
        return new String(chars);
    }

    private String up(String s, int t) {
        char[] chars = s.toCharArray();
        if (chars[t] == '9') {
            chars[t] = '0';
        } else {
            chars[t] += 1;
        }
        return new String(chars);
    }

    @Test
    public void test() {
        Assert.assertEquals(1, openLock(new String[]{"8888"}, "0009"));
        Assert.assertEquals(6, openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202"));
        Assert.assertEquals(-1, openLock(new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888"));
    }

}
