package ch07.se01;

import java.util.Scanner;

public class ScannerKeyBoardTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 使用回车作为分隔符
    //  sc.useDelimiter("\n");
        while (sc.hasNext()) {
            System.out.println("键盘输入的内容为：" + sc.next());
        }
    }
}
