package ds.ch08.exe;

import java.util.*;

/**
 08-图7 公路村村通 (30分)
 现有村落间道路的统计数据表中，列出了有可能建设成标准公路的若干条道路的成本，
 求使每个村落都有公路连通所需要的最低成本。

 输入格式:
 输入数据包括城镇数目正整数N（≤1000）和候选道路数目M（≤3N）；
 随后的M行对应M条道路，每行给出3个正整数，分别是该条道路直接连通的两个城镇的编号以及该道路改建的预算成本。
 为简单起见，城镇从1到N编号。

 输出格式:
 输出村村通需要的最低成本。如果输入数据不足以保证畅通，则输出−1，表示需要建设更多公路。
 */
public class CountryRoad {

    private static final int INF = Short.MAX_VALUE;

    /**
     * 方法1. 邻接矩阵 Prim 算法
     */
    public static void main(String[] args) {
        int[][] graph = buildGraphFromInput();
        int[] mst = prim(graph, 0);
        if (mst == null) {
            System.out.println(-1);
        } else {
            int roadLength = 0;
            for (int v = 0; v < graph.length; v++) {
                if (mst[v] != -1) {
                    roadLength += graph[v][mst[v]];
                }
            }
            System.out.println(roadLength);
        }
    }

    private static int[] prim(int[][] graph, int source) {
        int countries = graph.length;
        int count = 0;
        int[] dist = initDist(graph, source);
        int[] mst = initMst(graph, source);
        int v = source;
        while (true) {
            // 将 v 收录
            dist[v] = 0;
            count++;
            // 更新 v 的 未收录的邻接点 w 的 dist
            for (int w = 0; w < countries; w++) {
                if (dist[w] != 0 && dist[w] > graph[v][w]) {
                    dist[w] = graph[v][w];
                    mst[w] = v;
                }
            }

            // 返回未收录顶点中 dist 值最小的
            v = findMinDistVertex(dist, mst, source);
            if (v == -1) {
                break;
            }
        }
        if (count < countries - 1) {
            // 图不连通
            return null;
        }
        return mst;
    }

    private static int[] initMst(int[][] graph, int source) {
        int[] mst = new int[graph.length];
        for (int v = 0; v < graph.length; v++) {
            if (graph[source][v] != INF) {
                mst[v] = source;
            }
        }
        return mst;
    }

    private static int findMinDistVertex(int[] dist, int[] mst, int source) {
        int d = INF;
        int minDistVertex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (dist[v] != 0 && dist[v] < d) {
                d = dist[v];
                minDistVertex = v;
            }
        }
        return minDistVertex; // 表示不存在这样的点
    }

    private static int[] initDist(int[][] graph, int source) {
        int[] dist = new int[graph.length];
        for (int v = 0; v < graph.length; v++) {
            dist[v] = graph[source][v];
        }
        return dist;
    }

    public static int[][] buildGraphFromInput() {
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String[] metaData = firstLine.split("\\s+");
        int countries = Integer.parseInt(metaData[0]);
        int roads = Integer.parseInt(metaData[1]);

        int[][] graph = initGraph(countries);
        for (int i = 0; i < roads; i++) {
            String[] road = sc.nextLine().split("\\s+");
            int from = Integer.parseInt(road[0]) - 1;
            int to = Integer.parseInt(road[1]) - 1;
            int weight = Integer.parseInt(road[2]);
            graph[from][to] = weight;
            graph[to][from] = weight;
        }
        return graph;
    }

    private static int[][] initGraph(int countries) {
        int[][] graph = new int[countries][countries];
        for (int from = 0; from < countries; from++) {
            for (int to = 0; to <= from; to++) {
                if (from == to) {
                    graph[from][to] = 0;
                } else {
                    graph[from][to] = INF;
                    graph[to][from] = INF;
                }
            }
        }
        return graph;
    }

    static class Edge {
        public int from;
        public int to;
        public int weight;
    }

}
