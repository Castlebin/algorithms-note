package ds.ch06;

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
    int numOfVertex;

    /**
     * 边 数量
     */
    int numOfEdge;

    /**
     * 邻接表
     */
    VNode[] adjArray;

    /**
     * 顶点的头结点定义
     */
    class VNode {
        /**
         * 很多时候，顶点无数据，不需要
         */
        char data;

        // 邻接的所有顶点，放在一条链表上
        AdjEdge firstEdge;
    }

    /**
     * 邻接边的定义
     */
    class AdjEdge {
        int v;  // 邻接点下标
        int weight; // 权重
        AdjEdge next;   // 再下一个邻接点
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
    class Edge {
        int v, w;   // 有向边 <v, w>
        int weight; // 权重
    }

    /**
     * 初始化一个有 vertexNum 个顶点 的图，但是没有边
     * @param vertexNum 顶点数目
     */
    public GraphLinkedList(int vertexNum, /** 有的话，一般没有 int data */) {
        if (vertexNum > MAX_VERTEX_NUM) {
            throw new RuntimeException("最大顶点数目不能超过" + MAX_VERTEX_NUM);
        }
        this.numOfVertex = vertexNum;
        this.numOfEdge = 0;

        // 初始化邻接表
        this.adjArray = new VNode[numOfVertex];
        for (int i = 0; i < numOfVertex; i++) {
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
        e1.next = graph.adjArray[e1.v].firstEdge;
        graph.adjArray[e1.v].firstEdge = e1;

        // 无向图的话，还应该插入 <w, v>
        // 不写了

        graph.numOfEdge = graph.numOfEdge + 1;

        return graph;
    }

}
