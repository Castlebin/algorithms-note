package ds.ch07.exe;

import java.util.Scanner;

/**
    07-图4 哈利·波特的考试 (25分)
 */
public class HarrysExam {
    // 定义一下正无穷大
    private static final int INF = Short.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String[] metaData = firstLine.split("\\s+");
        int animals = Integer.parseInt(metaData[0]);
        int imprecations = Integer.parseInt(metaData[1]);

        int[][] graph = new int[animals][animals];
        int[][] dist = new int[animals][animals];
        for (int i = 0; i < imprecations; i++) {
            String[] eageData = sc.nextLine().split("\\s+");
            int m = Integer.parseInt(eageData[0]) - 1;
            int n = Integer.parseInt(eageData[1]) - 1;
            int weight = Integer.parseInt(eageData[2]);
            graph[m][n] = weight;
            graph[n][m] = weight;
            dist[m][n] = weight;
            dist[n][m] = weight;
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (i == j) {

                } else if (dist[i][j] == 0) {
                    graph[i][j] = INF;    // 防溢出，😌
                    dist[i][j] = INF;
                }
            }
        }

        prepareForExam(dist);
    }

    private static void prepareForExam(int[][] dist) {
        int num = dist.length;
        int[] maxLength = new int[num];
        for (int k = 0; k < num; k++) {
            for (int i = 0; i < num; i++) {
                int maxLen = dist[i][0];
                for (int j = 0; j < num; j++) {
                    int d = dist[i][k] + dist[k][j];    // 注意防溢出，😌
                    if (d < dist[i][j]) {
                        dist[i][j] = d;
                    }
                    if (maxLen < dist[i][j]) {
                        maxLen = dist[i][j];
                    }
                    maxLength[i] = maxLen;
                }
            }
        }

        int maxImprecationLength = maxLength[0];
        int animal = 0;
        for (int i = 1; i < maxLength.length; i++) {
            if (maxLength[i] < maxImprecationLength) {
                maxImprecationLength = maxLength[i];
                animal = i;
            }
        }

        if (maxImprecationLength == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println((animal + 1) + " " + maxImprecationLength);
        }
    }
}
