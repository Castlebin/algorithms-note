package ch15.se10;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileVistorTest {
    public static void main(String[] args) throws IOException {
        // 使用Files.walkFileTree()来遍历文件和目录
        Files.walkFileTree(Paths.get("."), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.endsWith("poem.txt")) {
                    System.out.println("找到目标文件：" + file.toRealPath() + "，文件内容如下：");
                    Files.lines(file).forEach(System.out::println);
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
