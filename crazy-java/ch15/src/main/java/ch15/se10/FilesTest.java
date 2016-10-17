package ch15.se10;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
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

        List<String> poem = new ArrayList<String>() {{
            add("我爱你");
            add("但我不敢说出口");
            add("我怕我说了");
            add("就会死");
            add("我不怕死");
            add("我怕我死了");
            add("再也没有人像我这样爱你");
        }};
        // 直接将多行内容写入文件
        Files.write(Paths.get("poem.txt"), poem, StandardCharsets.UTF_8);

        // 使用Java 8 新增的Stream API列出当前目录下的所有文件和子目录
        Files.list(Paths.get(".")).forEach(System.out::println);

        // 读取文件内容
        Files.lines(Paths.get("poem.txt"), StandardCharsets.UTF_8).forEach(System.out::println);

        // FileStore类
        FileStore rootStore = Files.getFileStore(Paths.get("/"));
        System.out.println("总空间：" + rootStore.getTotalSpace());
        System.out.println("可用空间：" + rootStore.getUsableSpace());
    }
}
