package ch07.se01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerFileTest {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("pom.xml"));
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
    }
}
