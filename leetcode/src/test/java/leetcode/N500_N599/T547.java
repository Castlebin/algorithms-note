package leetcode.N500_N599;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * 547. 省份数量
 */

public class T547 {

    public int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;
        int count = 0;

        int[] connect = new int[N];
        Arrays.fill(connect, -1);

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isConnected[j][i] == 1) {
                    // 分别算出 j、i 两个节点当前的根节点 jj、ii，将 jj 挂到 ii
                    int ii = findRoot(connect, i);
                    int jj = findRoot(connect, j);
                    if (ii != jj) {
                        connect[jj] = ii;
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (connect[i] == -1) {
                count++;
            }
        }

        return count;
    }

    private int findRoot(int[] connect, int i) {
        int t = i;
        while (connect[t] != -1) {
            t = connect[t];
        }
        return t;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, findCircleNum(new int[][] {
                new int[] {1,1,0},
                new int[] {1,1,0},
                new int[] {0, 0, 1}
        }));
        Assert.assertEquals(1, findCircleNum(new int[][] {
                new int[] {1, 0, 0, 1},
                new int[] {0, 1, 1, 0},
                new int[] {0, 1, 1, 1},
                new int[] {1, 0, 1, 1}
        }));
    }
}
