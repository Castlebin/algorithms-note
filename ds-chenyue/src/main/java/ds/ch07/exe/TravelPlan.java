package ds.ch07.exe;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 07-图6 旅游规划 (25分)
 */
public class TravelPlan {

    // 定义一下正无穷大
    private static final int INF = Short.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] meta = sc.nextLine().split("\\s+");
        int cityCount = Integer.parseInt(meta[0]);
        int roadCount = Integer.parseInt(meta[1]);
        int sourceCity = Integer.parseInt(meta[2]);
        int destination = Integer.parseInt(meta[3]);
        RoadGraph graph = new RoadGraph(cityCount);
        int[] path = new int[cityCount];
        int[] dist = new int[cityCount];
        int[] cost = new int[cityCount];

        for (int i = 0; i < roadCount; i++) {
            String[] roadMeta = sc.nextLine().split("\\s+");
            graph.insertRoad(
                    Integer.parseInt(roadMeta[0]),
                    Integer.parseInt(roadMeta[1]),
                    Integer.parseInt(roadMeta[2]),
                    Integer.parseInt(roadMeta[3]));
        }
        initData(path, dist, cost);
        dijkstra(graph, path, dist, cost, sourceCity);
        System.out.println(dist[destination] + " " + cost[destination]);
    }

    private static void dijkstra(RoadGraph graph, int[] path, int[] dist, int[] cost,
                                 int sourceCity) {
        int cityCount = graph.cityCount;
        Road[][] roads = graph.roads;
        boolean[] collected = new boolean[cityCount];
        dist[sourceCity] = 0;
        cost[sourceCity] = 0;

        // 找到dist、cost中 未收录顶点的最小值 的顶点，进行收录
        while (true) {
            int v = -1;
            int d = INF;
            int c = INF;
            for (int i = 0; i < cityCount; i++) {
                if (!collected[i] && (
                    dist[i] < d || (dist[i] == d && cost[i] < c)
                        )) {
                    v = i;
                    d = dist[i];
                    c = cost[i];
                }
            }
            // 没有这样的顶点（所有顶点都被收录），退出
            if (v == -1) {
                break;
            }
            collected[v] = true;
            // 收录了v之后，与v直接相连的w，dist、cost，有可能会被改变、减小
            for (int w = 0; w < cityCount; w++) {
                if (roads[v][w] != null && !collected[w]) {
                    if (dist[w] > dist[v] + roads[v][w].distance
                        || (dist[w] == dist[v] + roads[v][w].distance
                            && cost[w] > cost[v] + roads[v][w].price)) {
                        dist[w] = dist[v] + roads[v][w].distance;
                        cost[w] = cost[v] + roads[v][w].price;
                        path[w] = v;
                    }
                }
            }
        }
    }

    private static void initData(int[] path, int[] dist, int[] cost) {
        Arrays.fill(path, -1);
        Arrays.fill(dist, INF);
        Arrays.fill(cost, INF);
    }

    static class RoadGraph {
        public int cityCount;
        public Road[][] roads;

        // 使用邻接矩阵存放图
        public RoadGraph(int cityCount) {
            this.cityCount = cityCount;
            roads = new Road[cityCount][cityCount];
        }

        public void insertRoad(int v, int w, int distance, int price) {
            Road road = new Road(distance, price);
            roads[v][w] = road;
            roads[w][v] = road;
        }
    }

    static class Road {
        public int distance;
        public int price;

        public Road(int distance, int price) {
            this.distance = distance;
            this.price = price;
        }
    }

}
