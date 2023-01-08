package ds.ch10.exe;

import java.util.Scanner;

public class Exe1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int[] countAge = new int[51];
        for (int i = 0; i < count; i++) {
            countAge[sc.nextInt()]++;
        }
        for (int i = 0; i < countAge.length; i++) {
            if (countAge[i] > 0) {
                System.out.println(i + ":" + countAge[i]);
            }
        }
    }
}
