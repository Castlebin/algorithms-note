package ds.ch06.exe;

import ds.ch06.GraphLinkedList;

import java.util.*;

/**
 06-图1 列出连通集 (25分)
 给定一个有N个顶点和E条边的无向图，请用DFS和BFS分别列出其所有的连通集。假设顶点从 0 到 N−1 编号。
 进行搜索时，假设我们总是从编号最小的顶点出发，按编号递增的顺序访问邻接点。

 输入格式:
 输入第1行给出2个整数N( 0< N ≤10 )和E，分别是图的顶点数和边数。
 随后E行，每行给出一条边的两个端点。每行中的数字之间用1空格分隔。

 输出格式:
 按照"{ v​1 v​2 ... v​k }"的格式，每行输出一个连通集。先输出DFS的结果，再输出BFS的结果。

 输入样例:
 8 6
 0 7
 0 1
 2 0
 4 1
 2 4
 3 5
 输出样例:
 { 0 1 4 2 7 }
 { 3 5 }
 { 6 }
 { 0 1 2 7 4 }
 { 3 5 }
 { 6 }
 */
public class ListComponents {
    public static void main(String[] args) {
        GraphLinkedList graph = GraphLinkedList.readAndBuildGraph();
    //    GraphLinkedArray graph = GraphLinkedArray.readAndBuildGraph();
        dfs(graph);
        bfs(graph);
    }

    public static void dfs(GraphLinkedList graph) {
        boolean[] visited = new boolean[graph.numOfVertex];
        for (int v = 0; v < graph.numOfVertex; v++) {
            boolean linked = false;
            List<Integer> resultSet = new ArrayList<>();
            if (!visited[v]) {
                linked = true;
                resultSet = dfs(graph, v, visited, resultSet);
            }
            if (linked) {
                StringBuilder sb = new StringBuilder("{");
                for (Integer re : resultSet) {
                    sb.append(" " + re);
                }
                sb.append(" }");
                System.out.println(sb.toString());
            }
        }
    }

    public static List<Integer> dfs(GraphLinkedList graph, int v, boolean[] visited,
                                    List<Integer> result) {
        visited[v] = true;
        result.add(v);
        // 找到v的邻接点
        SortedSet<Integer> linkedVertexs = new TreeSet<>();
        GraphLinkedList.AdjEdge edge = graph.adjArray[v].firstEdge;
        while (edge != null) {
            if (!visited[edge.v]) {
                linkedVertexs.add(edge.v);
            }
            edge = edge.next;
        }
        for (Integer w : linkedVertexs) {
            if (!visited[w]) {
                dfs(graph, w, visited, result);
            }
        }
        return result;
    }

    public static void bfs(GraphLinkedList graph) {
        boolean[] visited = new boolean[graph.numOfVertex];
        for (int v = 0; v < graph.numOfVertex; v++) {
            boolean linked = false;
            List<Integer> resultSet = new ArrayList<>();
            if (!visited[v]) {
                linked = true;
                Queue<Integer> queue = new LinkedList<>();
                queue.add(v);
                resultSet = bfs(graph, queue, visited, resultSet);
            }
            if (linked) {
                StringBuilder sb = new StringBuilder("{");
                for (Integer re : resultSet) {
                    sb.append(" " + re);
                }
                sb.append(" }");
                System.out.println(sb.toString());
            }
        }
    }

    public static List<Integer> bfs(GraphLinkedList graph, Queue<Integer> queue, boolean[] visited,
                                    List<Integer> result) {
        int v = queue.remove();
        if (visited[v]) {
            return result;
        }
        visited[v] = true;
        result.add(v);
        // 找到v的邻接点
        SortedSet<Integer> linkedVertexs = new TreeSet<>();
        GraphLinkedList.AdjEdge edge = graph.adjArray[v].firstEdge;
        while (edge != null) {
            if (!visited[edge.v]) {
                linkedVertexs.add(edge.v);
            }
            edge = edge.next;
        }
        queue.addAll(linkedVertexs);

        while (!queue.isEmpty()) {
            bfs(graph, queue, visited, result);
        }

        return result;
    }

}
