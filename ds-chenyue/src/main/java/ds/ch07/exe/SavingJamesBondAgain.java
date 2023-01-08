package ds.ch07.exe;

import java.util.*;

/**
    07-图5 Saving James Bond - Hard Version (30分)

 （直接 DFS 路径和答案不一致，why？

    因为要求最少跳的步骤，所以变为一个单源最短路径问题啊笨蛋（无权））

 因为只是无权图，所以用 BFS 就能解决

 */
public class SavingJamesBondAgain {

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

        int[] dist = new int[numsOfCor + 2];
        int[] path = new int[numsOfCor + 2];
        Arrays.fill(dist, -1);
        Arrays.fill(path, - 1);

        boolean canBeSafe = canBeSafe(islandDiameter, poolHalfSize, maxJumpDis, pos, dist, path);
        if (!canBeSafe) {
            System.out.print(0);
        } else {
            System.out.println(dist[dist.length - 1]);
            Stack<Integer> stack = new Stack<>();
            for (int last = path[path.length - 1]; last > 0; last = path[last]) {
                stack.push(last);
            }
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                Integer v = stack.pop();
                sb.append(pos[v - 1][0] + " " + pos[v - 1][1] + "\n");
            }
            System.out.print(sb.toString().trim());
        }
    }

    private static boolean canBeSafe(int islandDiameter, int poolHalfSize, int maxJumpDis, int[][] pos, int[] dist, int[] path) {
        if (bfsJump(dist, path, pos, maxJumpDis, poolHalfSize, islandDiameter)) {
            return true;
        }

        return false;
    }

    private static boolean canJump(int i, int j, int[][] pos, int maxJumpDis, int diameter) {
        if (i == 0) {
            return canFirstJump(pos[j - 1], maxJumpDis, diameter);
        }
        return canJump(pos[i - 1], pos[j - 1], maxJumpDis);
    }

    private static boolean bfsJump(int[] dist, int[] path, int[][] pos, int maxJumpDis, int poolHalfSize, int islandDiameter) {
        Queue<Integer> queue = new LinkedList<>();
        dist[0] = 0;
        queue.add(0);
        while (!queue.isEmpty()) {
            int v = queue.remove();
            if (isSafe(v, pos, islandDiameter, maxJumpDis, poolHalfSize)) {
                dist[dist.length - 1] = dist[v] + 1;
                path[dist.length - 1] = v;
                return true;
            }

            for (int w = 1; w <= pos.length; w++) {
                if (dist[w] == -1 && canJump(v, w, pos, maxJumpDis, islandDiameter)) {
                    dist[w] = dist[v] + 1;
                    path[w] = v;
                    queue.add(w);
                }
            }
        }

        return false;
    }

    private static boolean isSafe(int i, int[][] pos, int islandDiameter, int maxJumpDis, int poolHalfSize) {
        if (i == 0) {
            return canJumpDirect(islandDiameter, poolHalfSize, maxJumpDis);
        }

        int x = pos[i - 1][0], y = pos[i - 1][1];
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
