package ch15.se10;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
    public static void main(String[] args) {
        // 以当前目录来创建Path对象
        Path path = Paths.get(".");
        System.out.println("path包含的路径数量：" + path.getNameCount());// 1
        System.out.println("path的根路径：" + path.getRoot()); // 输出null，因为此处path为"."
        // 获取path对应的绝对路径
        Path absolutePath = path.toAbsolutePath();
        System.out.println("绝对路径：" + absolutePath);
        System.out.println("absolutePath的根路径：" + absolutePath.getRoot());
        System.out.println("absolutePath包含的路径层数：" + absolutePath.getNameCount());
        System.out.println(absolutePath.getName(2));

        // 以多个String构建Path对象
        Path path2 = Paths.get("/", "home", "heller");
        System.out.println(path2);
    }
}
