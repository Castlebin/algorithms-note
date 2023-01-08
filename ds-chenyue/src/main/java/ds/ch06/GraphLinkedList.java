package ds.ch06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 用 邻接表 表示 图
 */
public class GraphLinkedList {

    /**
     * 最大节点数
     */
    private static final int MAX_VERTEX_NUM = 1000;

    /**
     * 顶点 数量
     */
    public int numOfVertex;

    /**
     * 边 数量
     */
    int numOfEdge;

    /**
     * 邻接表
     */
    public VNode[] adjArray;

    /**
     * 顶点的头结点定义
     */
    public class VNode {
        /**
         * 很多时候，顶点无数据，不需要
         */
        char data;

        // 邻接的所有顶点，放在一条链表上
        public AdjEdge firstEdge;
    }

    /**
     * 邻接边的定义
     */
    public class AdjEdge {
        public int v;  // 邻接点下标
        public int weight; // 权重
        public AdjEdge next;   // 再下一个邻接点
    }

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
    public static class Edge {
        public int v, w;   // 有向边 <v, w>
        public int weight; // 权重
    }

    /**
     * 初始化一个有 vertexNum 个顶点 的图，但是没有边
     * @param vertexNum 顶点数目
     */
    public GraphLinkedList(int vertexNum) {
        if (vertexNum > MAX_VERTEX_NUM) {
            throw new RuntimeException("最大顶点数目不能超过" + MAX_VERTEX_NUM);
        }
        this.numOfVertex = vertexNum;
        this.numOfEdge = 0;

        // 初始化邻接表
        this.adjArray = new VNode[numOfVertex];
        for (int i = 0; i < numOfVertex; i++) {
            adjArray[i] = new VNode();
            adjArray[i].firstEdge = null;
            // 有data的话，可以加上data的初始化，一般没有
        }
    }

    /**
     * 往图里插入一条边
     */
    public GraphLinkedList insertEdge(GraphLinkedList graph, Edge edge) {
        // 每次都将新的邻接点 插入 表头，这样插入操作比较容易，不用做什么判断
        AdjEdge e1 = new AdjEdge();
        e1.v = edge.w;
        e1.weight = edge.weight;
        // 插入表头
        e1.next = graph.adjArray[edge.v].firstEdge;
        graph.adjArray[edge.v].firstEdge = e1;

        // 无向图的话，还应该插入 <w, v>
        AdjEdge e2 = new AdjEdge();
        e2.v = edge.v;
        e2.weight = edge.weight;
        // 插入表头
        e2.next = graph.adjArray[edge.w].firstEdge;
        graph.adjArray[edge.w].firstEdge = e2;

        return graph;
    }

    public static GraphLinkedList buildGraph(int nv, int ne, List<Edge> edges) {
        GraphLinkedList graph = new GraphLinkedList(nv);
        graph.numOfEdge = ne;
        for (Edge edge : edges) {
            graph.insertEdge(graph, edge);
        }
        return graph;
    }

    public static GraphLinkedList readAndBuildGraph() {
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
