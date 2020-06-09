package ds.ch07;

import ds.ch06.GraphLinkedList;

import java.util.LinkedList;
import java.util.Queue;

/**
    无权图 单源最短路径 - 简单的 BFS 即可

    以递增（非递减）的顺序，收录到原点距离最短的每个订单
 */
public class Unweighted {
    
    public DistPath[] unweighted(GraphLinkedList graph, int s) {
        DistPath[] result = new DistPath[graph.numOfVertex];
        initialDataPath(result);

        Queue<Integer> queue = new LinkedList<>();
        result[s].dist = 0;
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.remove();
            // 对于 v 的每个邻接点 w
            for (GraphLinkedList.AdjEdge edge = graph.adjArray[v].firstEdge; edge != null; edge = edge.next) {
                int w = edge.v;
                if (result[w].dist != Short.MAX_VALUE) {
                    result[w].dist = result[v].dist + 1;
                    result[w].path = v;
                    queue.add(w);
                }
            }
        }

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
