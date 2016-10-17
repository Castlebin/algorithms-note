package ch15.se10;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesTest {
    public static void main(String[] args) throws IOException {
        // 复制文件
        Files.copy(Paths.get(".gitignore"), new FileOutputStream(".gitignore.tmp"));
        // 判断文件是否为隐藏文件
        System.out.println(".gitignore文件是否为隐藏文件：" + Files.isHidden(Paths.get(".gitignore")));
        // 一次性读取文件所有行，使用utf-8编码
        List<String> lines = Files.readAllLines(Paths.get(".gitignore"), StandardCharsets.UTF_8);
        System.out.println(lines);

        // 获取指定文件的大小
        System.out.println("文件大小：" + Files.size(Paths.get(".gitignore")));

        // 直接将多行内容写入文件
        List<String> poem = new ArrayList<String>() {{
            add("aaaaa");
        }};
    }
}
