package ds.ch08.exe;

import java.util.*;

/**
 08-图8 How Long Does It Take (25分)
 */
public class HowLongDoesItTakes {

    public static void main(String[] args) {
        LGraph graph = buildGraphFromInput();
        calTime(graph);
    }

    private static void calTime(LGraph graph) {
        int numOfVertexes = graph.numOfVertexes;
        int[] earliest = new int[numOfVertexes];
        Queue<Integer> zeroDegreeNodeList = new LinkedList<>();
        for (int i = 0; i < numOfVertexes; i++) {
            if (graph.inDegrees[i] == 0) {
                zeroDegreeNodeList.add(i);
            }
        }
        if (zeroDegreeNodeList.size() == 0) {
            System.out.println("Impossible");
            return;
        }

        int[] inDegrees = Arrays.copyOf(graph.inDegrees, graph.inDegrees.length);
        int cnt = 0;
        int earliestTime = 0;
        while (!zeroDegreeNodeList.isEmpty()) {
            int v = zeroDegreeNodeList.remove();
            cnt++;
            LGraph.AdjEdge edge = graph.adjNodes[v].firstEdge;
            while (edge != null) {
                int w = edge.w;
                int newWeight = earliest[v] + edge.weight;
                if (earliest[w] < newWeight) {
                    earliest[w] = newWeight;
                    if (earliest[w] > earliestTime) {
                        earliestTime = earliest[w];
                    }
                }
                if (--inDegrees[w] == 0) {
                    zeroDegreeNodeList.add(w);
                }
                edge = edge.next;
            }
        }
        if (cnt != numOfVertexes) {
            System.out.println("Impossible");
            return;
        }
        System.out.println(earliestTime);
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
            int weight = Integer.parseInt(edgeData[2]);
            graph.insertEdge(v, w, weight);
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
            public int weight;
            public AdjEdge next;
        }

        public void insertEdge(int v, int w, int weight) {
            AdjEdge adjEdge = new AdjEdge();
            adjEdge.w = w;
            adjEdge.weight = weight;
            adjEdge.next = adjNodes[v].firstEdge;
            adjNodes[v].firstEdge = adjEdge;

            outDegrees[v] += outDegrees[v];
            inDegrees[w] += inDegrees[w];
        }
    }

}
