package ds.ch06.exe;

import java.util.Scanner;

/**
 * 拯救007，典型的 DFS 应用
 *
 * 06-图2 Saving James Bond - Easy Version (25分)
 *
 */
public class SavingJamesBond {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String[] meta = firstLine.split("\\s+");
        int numsOfCor = Integer.parseInt(meta[0]);
        int maxJumpDis = Integer.parseInt(meta[1]);
        int pos[][] = new int[numsOfCor][2];
        for (int i = 0; i < numsOfCor; i++) {
            String[] p = sc.nextLine().split("\\s+");
            pos[i][0] = Integer.parseInt(p[0]);
            pos[i][1] = Integer.parseInt(p[1]);
        }

        int islandDiameter = 15;
        int poolHalfSize = 50;

        boolean canBeSafe = canBeSafe(islandDiameter, poolHalfSize, maxJumpDis, pos);
        System.out.println(canBeSafe? "Yes":"No");
    }

    private static boolean canBeSafe(int islandDiameter, int poolHalfSize, int maxJumpDis, int[][] pos) {
        if (canJumpDirect(islandDiameter, poolHalfSize, maxJumpDis)) {
            return true;
        }

        int numsOfCor = pos.length;
        boolean[] visited = new boolean[numsOfCor];
        for (int i = 0; i < numsOfCor; i++) {
            if (!visited[i] && canFirstJump(pos[i], maxJumpDis, islandDiameter)) {
                if (dfsJump(i, visited, pos, maxJumpDis, poolHalfSize)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfsJump(int i, boolean[] visited, int[][] pos, int maxJumpDis, int poolHalfSize) {
        visited[i] = true;
        if (isSafe(pos[i], maxJumpDis, poolHalfSize)) {
            return true;
        }

        // 对于每个邻接点，开始执行dfs
        for (int w = 0; w < pos.length; w++) {
            if (!visited[w] && canJump(pos[i], pos[w], maxJumpDis)) {
                if (dfsJump(w, visited, pos, maxJumpDis, poolHalfSize)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isSafe(int[] po, int maxJumpDis, int poolHalfSize) {
        int x = po[0], y = po[1];
        if (maxJumpDis >= x + poolHalfSize
            || maxJumpDis >= poolHalfSize - x
            || maxJumpDis >= y + poolHalfSize
            || maxJumpDis >= poolHalfSize - y
            ) {
            return true;
        }
        return false;
    }

    private static boolean canJump(int[] vp, int[] wp, int maxJumpDis) {
        return maxJumpDis >= distance(vp[0], vp[1], wp[0], wp[1]);
    }

    private static boolean canFirstJump(int[] po, int maxJumpDis, int islandDiameter) {
        return maxJumpDis >= distance(0, 0, po[0], po[1], islandDiameter);
    }

    private static boolean canJumpDirect(int islandDiameter, int poolHalfSize, int maxJumpDis) {
        return maxJumpDis >= (poolHalfSize - islandDiameter / 2);
    }

    private static double distance(int x1, int y1, int x2, int y2) {
        return distance(x1, y1, x2, y2, 0);
    }

    private static double distance(int x1, int y1, int x2, int y2, int diameter) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) - diameter / 2.0;
    }

}
