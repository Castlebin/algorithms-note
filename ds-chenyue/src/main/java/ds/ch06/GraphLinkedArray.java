package ds.ch06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 用 邻接矩阵 表示 图
 */
public class GraphLinkedArray {

    /**
     * 最大节点数
     */
    private static final int MAX_VERTEX_NUM = 1000;

    /**
     * 顶点 数量
     */
    int numOfVertex;

    /**
     * 边 数量
     */
    int numOfEdge;

    /**
     * 邻接矩阵 (假设权重是整数)，若两个顶点之间没有边，权重定义为正无穷大
     */
    int[][] weightArray;

    /**
     * 存顶点的数据，假设为字符（很多时候，顶点无数据，不需要这个域）
     */
    char[] dataArray;

    /**
     * 定义顶点
     */
    class Vertex {
        /**
         * 很多时候，顶点无数据，不需要
         */
        char data;
        /**
         * 顶点编号
         */
        int number;
    }

    /**
     * 定义边
     */
    static class Edge {
        int v, w;   // 有向边 <v, w>
        int weight; // 权重
    }

    /**
     * 初始化一个有 vertexNum 个顶点 的图，但是没有边
     * @param vertexNum 顶点数目
     */
    public GraphLinkedArray(int vertexNum) {
        if (vertexNum > MAX_VERTEX_NUM) {
            throw new RuntimeException("最大顶点数目不能超过" + MAX_VERTEX_NUM);
        }
        this.numOfVertex = vertexNum;
        this.numOfEdge = 0;
        // 初始化邻接矩阵
        this.weightArray = new int[numOfVertex][numOfVertex];
        for (int i = 0; i < numOfVertex; i++) {
            for (int j = 0; j < numOfVertex; j++) {
                weightArray[i][j] = Integer.MAX_VALUE;
            }
        }
        this.dataArray = new char[numOfVertex];
    }

    /**
     * 往图里插入一条边
     */
    public GraphLinkedArray insertEdge(GraphLinkedArray graph, Edge edge) {
        graph.weightArray[edge.v][edge.w] = edge.weight;

        // 无向图的话，还要插入边 <w, v>
        graph.weightArray[edge.w][edge.v] = edge.weight;

        return graph;
    }

    public static GraphLinkedArray buildGraph(int nv, int ne, List<Edge> edges) {
        GraphLinkedArray graph = new GraphLinkedArray(nv);
        graph.numOfEdge = ne;
        for (Edge edge : edges) {
            graph.insertEdge(graph, edge);
        }
        return graph;
    }

    public static GraphLinkedArray readAndBuildGraph() {
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String[] num = firstLine.split("\\s+");
        int nv = Integer.parseInt(num[0]);
        int ne = Integer.parseInt(num[1]);
        List<Edge> edges = new ArrayList<>(ne);
        for(int i = 0; i < ne; i++) {
            String eD = sc.nextLine();
            String[] edgeData = eD.split("\\s+");
            Edge edge = new Edge();
            edge.v = Integer.parseInt(edgeData[0]);
            edge.w = Integer.parseInt(edgeData[1]);
            if (edgeData.length >= 3) {
                edge.weight = Integer.parseInt(edgeData[2]);
            }
            edges.add(edge);
        }
        return buildGraph(nv, ne, edges);
    }

}
