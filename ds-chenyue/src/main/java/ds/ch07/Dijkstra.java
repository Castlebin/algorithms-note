package ds.ch07;

import ds.ch06.GraphLinkedList;

/**
    有权图 单源最短路径 - Dijkstra 算法

    以递增（非递减）的顺序，收录到原点距离最短的每个订单

 todo
 */
public class Dijkstra {

    public DistPath[] dijkstra(GraphLinkedList graph, int s) {
        DistPath[] result = new DistPath[graph.numOfVertex];
        initialDataPath(result);

        return result;
    }

    private void initialDataPath(DistPath[] result) {
        for (DistPath distPath : result) {
            if (distPath == null) {
                distPath = new DistPath();
            }
            distPath.dist = Short.MAX_VALUE;
            distPath.path = -1;
        }
    }

    class DistPath {
        public int dist;
        public int path;
    }

}
