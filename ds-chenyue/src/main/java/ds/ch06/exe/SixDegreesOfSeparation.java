package ds.ch06.exe;

import ds.ch06.GraphLinkedList;

import java.util.*;

import static ds.ch06.GraphLinkedList.buildGraph;

/**
 * 06-图3 六度空间 (30分)
 * “六度空间”理论又称作“六度分隔（Six Degrees of Separation）”理论。
 * 这个理论可以通俗地阐述为：“你和任何一个陌生人之间所间隔的人不会超过六个，
 * 也就是说，最多通过五个人你就能够认识任何一个陌生人。”
 *
 * 典型的 BFS 应用
 */
public class SixDegreesOfSeparation {
    public static void main(String[] args) {
        // 使用 邻接表 表示图
        GraphLinkedList graph = readAndBuildGraph();
        sds(graph);
    }

    private static void sds(GraphLinkedList graph) {
        int numOfVertex = graph.numOfVertex;
        for (int v = 0; v < numOfVertex; v++) {
            int count = sds(graph, v);
            System.out.printf("%d: %.2f%%\n", v + 1, count * 100.0 / numOfVertex);
        }
    }

    private static int sds(GraphLinkedList graph, int v) {
        boolean[] visited = new boolean[graph.numOfVertex];
        int[] level = new int[graph.numOfVertex];
        int count = 0;

        Queue<Integer> queue = new LinkedList<>();
        visited[v] = true;
        queue.add(v);
        count++;
        while (!queue.isEmpty()) {
            int n = queue.remove();

            // 对于 n 的每个邻接点 w
            GraphLinkedList.AdjEdge edge = graph.adjArray[n].firstEdge;
            while (edge != null) {
                if (!visited[edge.v] && level[n] < 6) {
                    visited[edge.v] = true;
                    queue.add(edge.v);
                    count++;
                    level[edge.v] = level[n] + 1;
                }
                edge = edge.next;
            }
        }

        return count;
    }

    static GraphLinkedList readAndBuildGraph() {
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String[] num = firstLine.split("\\s+");
        int nv = Integer.parseInt(num[0]);
        int ne = Integer.parseInt(num[1]);
        List<GraphLinkedList.Edge> edges = new ArrayList<>(ne);
        for(int i = 0; i < ne; i++) {
            String eD = sc.nextLine();
            String[] edgeData = eD.split("\\s+");
            GraphLinkedList.Edge edge = new GraphLinkedList.Edge();
            edge.v = Integer.parseInt(edgeData[0]) - 1;
            edge.w = Integer.parseInt(edgeData[1]) - 1;
            if (edgeData.length >= 3) {
                edge.weight = Integer.parseInt(edgeData[2]);
            }
            edges.add(edge);
        }
        return buildGraph(nv, ne, edges);
    }

}
