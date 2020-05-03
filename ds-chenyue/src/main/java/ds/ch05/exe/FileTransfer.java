package ds.ch05.exe;

import java.util.*;

/*
05-树8 File Transfer (25分)
We have a network of computers and a list of bi-directional connections. Each of these connections allows a file transfer
from one computer to another. Is it possible to send a file from any computer on the network to any other?

Input Specification:
Each input file contains one test case. For each test case, the first line contains N (2≤N≤10
​4
​​ ), the total number of computers in a network. Each computer in the network is then represented by a positive integer
between 1 and N. Then in the following lines, the input is given in the format:

I c1 c2

where I stands for inputting a connection between c1 and c2; or

C c1 c2

where C stands for checking if it is possible to transfer files between c1 and c2; or

S

where S stands for stopping this case.

Output Specification:
For each C case, print in one line the word "yes" or "no" if it is possible or impossible to transfer files between c1 and c2, respectively. At the end of each case, print in one line "The network is connected." if there is a path between any pair of computers; or "There are k components." where k is the number of connected components in this network.

Sample Input 1:
5
C 3 2
I 3 2
C 1 5
I 4 5
I 2 4
C 3 5
S

Sample Output 1:
no
no
yes
There are 2 components.

Sample Input 2:
5
C 3 2
I 3 2
C 1 5
I 4 5
I 2 4
C 3 5
I 1 3
C 1 5
S



Sample Output 2:
no
no
yes
yes
The network is connected.

 */
public class FileTransfer {
    public static Map<Integer, Set<Integer>> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = Integer.parseInt(sc.nextLine());
        int[] metaData = initMetaData(size);
        while (true) {
            String line = sc.nextLine();
            if (line.equals("S")) {
                int components = countComponents(metaData);
                if (components == 1) {
                    System.out.println("The network is connected.");
                } else {
                    System.out.println("There are " + components + " components.");
                }
                break;
            }
            String[] op = line.split("\\s+");
            if (op[0].equals("C")) {
                int c1 = Integer.parseInt(op[1]) - 1;
                int c2 = Integer.parseInt(op[2]) - 1;
                System.out.println(checkConnect(metaData, c1, c2)? "yes" : "no");
            } else if (op[0].equals("I")) {
                int c1 = Integer.parseInt(op[1]) - 1;
                int c2 = Integer.parseInt(op[2]) - 1;
                connect(metaData, c1, c2);
            }
        }
    }

    private static int find(int[] metaData, int c) {
        if (metaData[c] < 0) {
            return c;
        } else {
            return metaData[c] = find(metaData, metaData[c]);
        }
    }

    private static void connect(int[] metaData, int c1, int c2) {
        int s1 = find(metaData, c1);
        int s2 = find(metaData, c2);
        if (s1 != s2) {
            int meta = metaData[s1] + metaData[s2];
            if (metaData[s1] < metaData[s2]) {
            //    metaData[s1] = s2;
                metaData[s2] = meta;

                // 路径压缩
                map.get(s2).addAll(map.get(s1));
                for (Integer i : map.get(s1)) {
                    metaData[i] = s2;
                }
                map.get(s1).clear();
            } else {
             //   metaData[s2] = s1;
                metaData[s1] = meta;

                // 路径压缩
                map.get(s1).addAll(map.get(s2));
                for (Integer i : map.get(s2)) {
                    metaData[i] = s1;
                }
                map.get(s2).clear();
            }
        }
    }

    private static boolean checkConnect(int[] metaData, int c1, int c2) {
        int s1 = find(metaData, c1);
        int s2 = find(metaData, c2);
        return s1 == s2;
    }

    private static int countComponents(int[] metaData) {
        int count = 0;
        for (int i = 0; i < metaData.length; i++) {
            if (metaData[i] < 0) {
                count++;
            }
        }
        return count;
    }

    private static int[] initMetaData(int size) {
        int[] metaData = new int[size];
        for (int i = 0; i < size; i++) {
            metaData[i] = -1;

            Set<Integer> set = new HashSet<>();
            set.add(i);
            map.put(i, set);
        }
        return metaData;
    }

}
