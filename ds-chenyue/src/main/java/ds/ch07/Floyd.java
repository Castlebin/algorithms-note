package ds.ch07;

import ds.ch06.GraphLinkedArray;

/**
    多源最短路径 - Floyd算法
    对 稠密图 合适，用 邻接矩阵 表示图
 */
public class Floyd {

    // 定义一下正无穷大
    private static final int INF = Short.MAX_VALUE;

    public DistPath[][] floyd(GraphLinkedArray graph) {
        int numOfVertex = graph.numOfVertex;
        DistPath[][] distPath = new DistPath[numOfVertex][numOfVertex];
        initialDistPath(distPath, graph);

        for (int k = 0; k < numOfVertex; k++) {
            for (int i = 0; i < numOfVertex; i++) {
                for (int j = 0; j < numOfVertex; j++) {
                    if (distPath[i][k].dist + distPath[k][j].dist < distPath[i][j].dist) {
                        distPath[i][j].dist = distPath[i][k].dist + distPath[k][j].dist;
                        // 若发现负值圈，不能解决
                        if (i == j && distPath[i][j].dist < 0) {
                            throw new RuntimeException("发现负值圈，不能解决!");
                        }
                        distPath[i][j].path = k;
                    }
                }
            }
        }

        return distPath;
    }

    private void initialDistPath(DistPath[][] distPath, GraphLinkedArray graph) {
        int[][] weightArray = graph.weightArray;
        for (int i = 0; i < weightArray.length; i++) {
            for (int j = 0; j < weightArray[i].length; j++) {
                if (distPath[i][j] == null) {
                    distPath[i][j] = new DistPath();
                }
                distPath[i][j].dist = weightArray[i][j];
                distPath[i][j].path = -1;
            }
        }
    }

    class DistPath {
        public int dist;
        public int path;
    }

}
