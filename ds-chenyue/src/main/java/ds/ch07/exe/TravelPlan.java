package ds.ch07.exe;

import java.util.PriorityQueue;
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
        DistPath[] distPaths = new DistPath[cityCount];

        for (int i = 0; i < roadCount; i++) {
            String[] roadMeta = sc.nextLine().split("\\s+");
            graph.insertRoad(
                    Integer.parseInt(roadMeta[0]),
                    Integer.parseInt(roadMeta[1]),
                    Integer.parseInt(roadMeta[2]),
                    Integer.parseInt(roadMeta[3]));
        }
        initData(distPaths);
        dijkstra(graph, distPaths, sourceCity);
        System.out.println(distPaths[destination].dist + " " + distPaths[destination].cost);
    }

    private static void dijkstra(RoadGraph graph, DistPath[] distPaths,
                                 int sourceCity) {
        int cityCount = graph.cityCount;
        Road[] roads = graph.roads;
        boolean[] collected = new boolean[cityCount];
        distPaths[sourceCity].dist = 0;
        distPaths[sourceCity].cost = 0;

        PriorityQueue<DistPath> heap = new PriorityQueue<>((o1, o2) -> {
            if (o1.dist != o2.dist) {
                return o1.dist - o2.dist;
            } else {
                return o1.cost - o2.cost;
            }
        });
        heap.add(distPaths[sourceCity]);

        // 找到dist、cost中 未收录顶点的最小值 的顶点，进行收录
        while (true) {
            // 没有这样的顶点（所有顶点都被收录），退出
            if (heap.isEmpty()) {
                break;
            }
            DistPath vd = heap.remove();
            int v = vd.i;
            collected[v] = true;
            // 收录了v之后，与v直接相连的w，dist、cost，有可能会被改变、减小
            for (Road road = roads[v]; road != null; road = road.next) {
                int w = road.w;
                if (!collected[w]) {
                    if (distPaths[w].dist > distPaths[v].dist + road.distance
                            || (distPaths[w].dist == distPaths[v].dist + road.distance
                            && distPaths[w].cost > distPaths[v].cost + road.price)) {
                        distPaths[w].dist = distPaths[v].dist + road.distance;
                        distPaths[w].cost = distPaths[v].cost + road.price;
                        distPaths[w].path = v;
                        heap.remove(distPaths[w]);
                        heap.add(distPaths[w]);
                    }
                }
            }
        }
    }

    private static void initData(DistPath[] distPaths) {
        for (int i = 0; i < distPaths.length; i++) {
            DistPath distPath = new DistPath();
            distPaths[i] = distPath;
            distPath.i = i;
            distPath.path = -1;
            distPath.dist = INF;
            distPath.cost = INF;
        }
    }

    static class RoadGraph {
        public int cityCount;
        public Road[] roads;

        // 使用邻接矩阵存放图
        public RoadGraph(int cityCount) {
            this.cityCount = cityCount;
            roads = new Road[cityCount];
        }

        public void insertRoad(int v, int w, int distance, int price) {
            Road road = new Road(w, distance, price);
            road.next = this.roads[v];
            roads[v] = road;

            Road road_w = new Road(v, distance, price);
            road_w.next = this.roads[w];
            roads[w] = road_w;
        }
    }

    static class DistPath {
        int i;
        int dist;
        int path;
        int cost;
        boolean collected;

        @Override
        public boolean equals(Object distPath) {
            return this.i == ((DistPath) distPath).i;
        }
    }

    static class Road {
        public int distance;
        public int price;
        public int w;
        public Road next;

        public Road(int w, int distance, int price) {
            this.w = w;
            this.distance = distance;
            this.price = price;
        }
    }

}
