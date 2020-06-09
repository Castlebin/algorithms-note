package ds.ch07;

import ds.ch06.GraphLinkedArray;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
    多源最短路径 - Floyd算法
    对 稠密图 合适，用 邻接矩阵 表示图

    ( 思考下，最短路径经过的顶点如何得到？ done)
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

    /**
     *  获取到Floyd结果矩阵后，进一步任意两个节点i、j之间的最短路径
     */
    public List<Integer> getShortestPath(int i, int j, DistPath[][] floydDistPath) {
        Assert.assertFalse("请输入两个不同的顶点", i == j);
        Assert.assertFalse("两点之间不存在路径", floydDistPath[i][j].dist == INF);

        List<Integer> path = new ArrayList<>();
        getShortestPath(i, j, floydDistPath, path);
        return path;
    }

    public void getShortestPath(int i, int j, DistPath[][] floydDistPath, List<Integer> path) {
        if (i != j) {
            Assert.assertFalse("两点之间不存在路径", floydDistPath[i][j].dist == INF);
            int k = floydDistPath[i][j].path;

            // i 到 j 的最短路径，一定经过 k，所以可以通过递归来解决这个问题
            // 最短路径一定由 <i .. k>   +   k    +  <k .. j> 组成
            getShortestPath(i, k, floydDistPath, path);
            path.add(k);
            getShortestPath(k, j, floydDistPath, path);
        }
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
