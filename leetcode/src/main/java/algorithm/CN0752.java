package algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/open-the-lock/

 752. 打开转盘锁
 你有一个带有四个圆形拨轮的转盘锁。
 每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。

 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。

 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。

 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。

 */

/**
 使用 BFS 或者 双向 的 BFS 进行扩展 解决
 */
public class CN0752 {
    /** 密码盘的位数 */
    int DIGIT_COUNT = 4;

    public int openLock(String[] deadends, String target) {
        char[] initCharArr = new char[DIGIT_COUNT];
        Arrays.fill(initCharArr, '0');
        String init = new String(initCharArr);

        if (target == null || target.length() != DIGIT_COUNT) {
            return -1;
        }
        if (target.equals(init)) {
            return 0;
        }

        // 记录已经访问过的密码组合
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        if (visited.contains(init)) {
            return -1;
        }

        Set<String> q1 = new HashSet<>();
        q1.add(init);
        Set<String> q2 = new HashSet<>();
        q2.add(target);
        int step = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.size() > q2.size()) {
                Set<String> temp = q1;
                q1 = q2;
                q2 = temp;
            }

            Set<String> temp = new HashSet<>();

            for (String num : q1) {
                if (q2.contains(num)) {
                    return step;
                }
                visited.add(num);
                for (int index = 0; index < DIGIT_COUNT; index++) {
                    String plusOne = plusOne(num, index);
                    if (!visited.contains(plusOne)) {
                        temp.add(plusOne);
                    }
                    String minusOne = minusOne(num, index);
                    if (!visited.contains(minusOne)) {
                        temp.add(minusOne);
                    }
                }
            }
            step++;

            q1 = q2;
            q2 = temp;
        }

        return -1;
    }
    /** 将数字 num 中的第 index 位 往上拨一位 */
    private String plusOne(String num, int index) {
        char[] charArr = num.toCharArray();
        if (charArr[index] == '9') {
            charArr[index] = '0';
        } else {
            charArr[index] += 1;
        }
        return new String(charArr);
    }
    /** 将数字 num 中的第 index 位 往下拨一位 */
    private String minusOne(String num, int index) {
        char[] charArr = num.toCharArray();
        if (charArr[index] == '0') {
            charArr[index] = '9';
        } else {
            charArr[index] -= 1;
        }
        return new String(charArr);
    }

    @Test
    public void testOpenLock() {
        Assert.assertEquals(1, openLock(new String[]{"8888"}, "0009"));
        Assert.assertEquals(-1, openLock(new String[]{"0000"}, "8888"));
        Assert.assertEquals(-1,
                openLock(new String[]{"8887","8889","8878","8898",
                        "8788","8988","7888","9888"},
                        "8888"));
    }

}
