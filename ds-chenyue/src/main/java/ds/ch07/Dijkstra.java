package ds.ch07;

import ds.ch06.GraphLinkedList;

/**
    有权图 单源最短路径 - Dijkstra 算法

    以递增（非递减）的顺序，收录到原点距离最短的每个订单
 */
public class Dijkstra {
    // 定义一下正无穷大
    private static final int INF = Short.MAX_VALUE;
    private static final int NOT_EXIST = -1;

    public DistPath[] dijkstra(GraphLinkedList graph, int s) {
        DistPath[] distPaths = new DistPath[graph.numOfVertex];
        initialDataPath(distPaths);

        boolean[] collected = new boolean[graph.numOfVertex];
        distPaths[s].dist = 0;

        while (true) {
            // 找出未收录顶点中 dist 值最小的顶点，进行收录
            int v = findMinDist(graph, distPaths, collected);
            if (v == NOT_EXIST) {
                break;
            }
            // 将 v 收录
            collected[v] = true;
            // 收录了 v，那么 v 的邻接点 w(并且 w 还没有被收录) 的 dist 值可能会发生变化
            for (GraphLinkedList.AdjEdge edge = graph.adjArray[v].firstEdge; edge != null; edge = edge.next) {
                int w = edge.v;
                int weight = edge.weight;
                if (weight < 0) {
                    throw new RuntimeException("dijkstra算法不能解决负值边的情况");
                }
                if (!collected[w] && (distPaths[v].dist + edge.weight) < distPaths[w].dist) {
                    // 更新路径和路径长度
                    distPaths[w].dist = distPaths[v].dist + edge.weight;
                    distPaths[w].path = v;
                }
            }
        }

        return distPaths;
    }

    private boolean checkCollected(boolean[] collected) {
        for (boolean collect : collected) {
            if (!collect) {
                return false;
            }
        }
        return true;
    }

    private int findMinDist(GraphLinkedList graph, DistPath[] distPaths, boolean[] collected) {
        int v = NOT_EXIST;// NOT_EXIST 表示不存在这样的顶点
        int dist = INF;
        for (int i = 0; i < distPaths.length; i++) {
            // 未收录顶点中，dist最小值的顶点
            if (!collected[i] && distPaths[i].dist < dist) {
                dist = distPaths[i].dist;
                v = i;
            }
        }

        return v;
    }

    private void initialDataPath(DistPath[] distPaths) {
        for (DistPath distPath : distPaths) {
            if (distPath == null) {
                distPath = new DistPath();
            }
            distPath.dist = INF;
            distPath.path = -1;
        }
    }

    class DistPath {
        public int dist;
        public int path;
    }

}
