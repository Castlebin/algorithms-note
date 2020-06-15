package ds.ch08.exe;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

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
        Graph graph = buildGraphFromInput();
        int[] mst = prim(graph);
    }

    private static int[] prim(Graph graph) {
        int countries = graph.graph.length;
        int count = 0;
        int[][] dist = initDist(graph.graph, 0);
        boolean[] collected = new boolean[countries];
        int[] mst = new int[countries];

        mst[0] = -1;
        collected[0] = true;
        count++;
        while (true) {
            // todo
            break;
        }

        return null;
    }

    private static int[][] initDist(int[][] graph, int source) {
        int countries = graph.length;
        int[][] dist = new int[countries][countries];

        for (int from = 0; from < countries; from++) {
            for (int to = 0; to <= from; to++) {
                if (from == to) {
                    dist[from][to] = 0;
                } else if (from == source) {
                    dist[from][to] = graph[from][to];
                    dist[to][from] = graph[from][to];
                } else {
                    dist[from][to] = INF;
                    dist[to][from] = INF;
                }
            }
        }

        return dist;
    }

    public static Graph buildGraphFromInput() {
        Graph graph = new Graph();
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String[] metaData = firstLine.split("\\s+");
        int countries = Integer.parseInt(metaData[0]);
        int roads = Integer.parseInt(metaData[1]);

        graph.graph = initGraph(countries);
        for (int i = 0; i < roads; i++) {
            String[] road = sc.nextLine().split("\\s+");
            int from = Integer.parseInt(road[0]) - 1;
            int to = Integer.parseInt(road[1]) - 1;
            int weight = Integer.parseInt(road[2]);
            graph.graph[from][to] = weight;
            graph.graph[to][from] = weight;

            Edge edge = new Edge();
            edge.from = from;
            edge.to = to;
            edge.weight = weight;
            graph.edgeHeap.add(edge);
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

    static class Graph {
        public int[][] graph;
        public PriorityQueue<Edge> edgeHeap = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
    }

    static class Edge {
        public int from;
        public int to;
        public int weight;
    }

}
