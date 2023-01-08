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
public class CountryRoad_2 {

    /**
     * 方法2. Kruskal 算法（并没有按邻接表储存图，取巧了）
     */
    public static void main(String[] args) {
        Graph graph = buildGraphFromInput();
        List<Edge> mst = kruskal(graph);
        if (mst == null) {
            System.out.println(-1);
        } else {
            int roadLength = 0;
            for (Edge edge : mst) {
                roadLength = roadLength + edge.weight;
            }
            System.out.println(roadLength);
        }
    }

    private static List<Edge> kruskal(Graph graph) {
        int countries = graph.vertex;
        int needEdages = countries - 1;
        // 并查集
        int[] parent = new int[countries];
        Arrays.fill(parent, -1);
        PriorityQueue<Edge> edgeHeap = graph.edgeHeap;
        List<Edge> result = new ArrayList<>();
        while (!edgeHeap.isEmpty() && result.size() < needEdages) {
            Edge edge = edgeHeap.remove();
            // 如果 这条边 不与 已经收录的边 形成环，那么，可以被收录
            int rootFrom = findRoot(parent, edge.from);
            int rootTo = findRoot(parent, edge.to);
            if (rootFrom != rootTo) {
                // 收录边，更新并查集
                result.add(edge);

                // 更新并查集(此处固定的将from并向to)
                parent[rootTo] = parent[rootFrom] + parent[rootTo];
                parent[rootFrom] = rootTo;
            }
        }

        if (result.size() == needEdages) {
            return result;
        }

        // 图不连通
        return null;
    }

    private static int findRoot(int[] parent, int v) {
        if (parent[v] < 0) {
            return v;
        } else {
            return parent[v] = findRoot(parent, parent[v]);
        }
    }

    public static Graph buildGraphFromInput() {
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String[] metaData = firstLine.split("\\s+");
        int countries = Integer.parseInt(metaData[0]);
        int roads = Integer.parseInt(metaData[1]);

        Graph graph = new Graph();
        graph.vertex = countries;
        for (int i = 0; i < roads; i++) {
            String[] road = sc.nextLine().split("\\s+");
            int from = Integer.parseInt(road[0]) - 1;
            int to = Integer.parseInt(road[1]) - 1;
            int weight = Integer.parseInt(road[2]);

            Edge edge = new Edge();
            edge.from = from;
            edge.to = to;
            edge.weight = weight;
            graph.edgeHeap.add(edge);
        }
        return graph;
    }

    static class Graph {
        public int vertex;
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
