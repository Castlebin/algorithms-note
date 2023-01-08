package ds.ch08;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 拓扑排序
 */
public class TopSort {

    public static void main(String[] args) {
        LGraph graph = buildGraphFromInput();
        try {
            int[] topSortResult = topSort(graph);
            if (topSortResult != null) {
                System.out.println(Arrays.toString(topSortResult));
            }
        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }

    private static int[] topSort(LGraph graph) {
        int numOfVertexes = graph.numOfVertexes;
        Queue<Integer> zeroDegreeNodeList = new LinkedList<>();
        for (int i = 0; i < numOfVertexes; i++) {
            if (graph.inDegrees[i] == 0) {
                zeroDegreeNodeList.add(i);
            }
        }
        if (zeroDegreeNodeList.size() == 0) {
            return null;
        }

        int[] inDegrees = Arrays.copyOf(graph.inDegrees, graph.inDegrees.length);
        int cnt = 0;
        int[] topSortResult = new int[numOfVertexes];
        while (!zeroDegreeNodeList.isEmpty()) {
            int v = zeroDegreeNodeList.remove();
            topSortResult[cnt++] = v;
            LGraph.AdjEdge edge = graph.adjNodes[v].firstEdge;
            while (edge != null) {
                int w = edge.w;
                inDegrees[w] = inDegrees[w] - 1;
                if (inDegrees[w] == 0) {
                    zeroDegreeNodeList.add(w);
                }
                edge = edge.next;
            }
        }
        if (cnt != numOfVertexes) {
            return null;
        }
        return topSortResult;
    }

    private static LGraph buildGraphFromInput() {
        Scanner sc = new Scanner(System.in);
        String[] metaData = sc.nextLine().split("\\s+");
        int numOfVertexes = Integer.parseInt(metaData[0]);
        int numOfEdges = Integer.parseInt(metaData[1]);

        LGraph graph = new LGraph(numOfVertexes, numOfEdges);

        for (int i = 0; i < numOfEdges; i++) {
            String[] edgeData = sc.nextLine().split("\\s+");
            int v = Integer.parseInt(edgeData[0]);
            int w = Integer.parseInt(edgeData[1]);
            graph.insertEdge(v, w);
        }

        return graph;
    }

    public static class LGraph {
        public int numOfVertexes;
        public int numOfEdges;

        public AdjNode[] adjNodes;

        public int[] inDegrees;
        public int[] outDegrees;

        public LGraph(int numOfVertexes, int numOfEdges) {
            this.numOfVertexes = numOfVertexes;
            this.numOfEdges = numOfEdges;

            this.adjNodes = new AdjNode[numOfVertexes];

            inDegrees = new int[numOfVertexes];
            outDegrees = new int[numOfVertexes];
            for (int i = 0; i < numOfVertexes; i++) {
                adjNodes[i] = new AdjNode();
                adjNodes[i].firstEdge = null;
                // 有data的话，可以加上data的初始化，一般没有
            }
        }

        public static class AdjNode {
            public AdjEdge firstEdge;
        }

        public static class AdjEdge {
            public int w;
            public AdjEdge next;
        }

        public void insertEdge(int v, int w) {
            AdjEdge adjEdge = new AdjEdge();
            adjEdge.w = w;
            adjEdge.next = adjNodes[v].firstEdge;
            adjNodes[v].firstEdge = adjEdge;

            outDegrees[v] = outDegrees[v] + 1;
            inDegrees[w] = inDegrees[w] + 1;
        }
    }

}
