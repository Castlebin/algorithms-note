package ch15.se01;

import java.io.File;

public class FilenameFilterTest {
    public static void main(String[] args) {
        File file = new File(".");
        String[] nameList = file.list((dir, name) -> name.endsWith(".java")
                || new File(name).isDirectory() || name.endsWith(".md"));
        for (String name : nameList) {
            System.out.println(name);
        }
    }
}
