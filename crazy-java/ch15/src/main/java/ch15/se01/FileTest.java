package ch15.se01;

import java.io.File;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) throws IOException {
        // 以当前路径来创建一个File对象
        File file = new File(".");
        // 获取文件名，输出点(.)
        System.out.println(file.getName());
        // 获取相对路径的父路径可能出错，下面代码输出null
        System.out.println(file.getParent());
        // 获取绝对路径
        System.out.println(file.getAbsoluteFile());
        // 获取绝对路径的父路径
        System.out.println(file.getAbsoluteFile().getParent());

        // 在当前目录下创建一个临时文件
        File tmpFile = File.createTempFile("aaa", ".tmp", file);
        // 指定当JVM退出时删除该文件
        tmpFile.deleteOnExit();

        // 以系统时间创建一个新文件
        File newFile = new File(System.currentTimeMillis() + ".tmp");
        // 文件在磁盘上还没有创建，所以输出false
        System.out.println("newFile对象是否存在：" + newFile.exists());
        // 在磁盘上创建该文件
        newFile.createNewFile();
        // 好了，现在文件创建好了，存在于磁盘上了
        System.out.println("newFile对象是否存在：" + newFile.exists());
        // 尝试再次创建，创建失败！
        System.out.println(newFile.createNewFile());
        // 尝试创建同名的目录，创建失败！
        System.out.println(newFile.mkdir());
        // 使用list()方法列出当前目录下所有的文件和目录
        String[] fileList = file.list();
        System.out.println("====当前路径下所有文件和目录如下====");
        for (String fileName : fileList) {
            System.out.println(fileName);
        }

        // listRoots()静态方法列出所有的磁盘根路径
        File[] roots = File.listRoots();
        System.out.println("====系统的所有根路径如下====");
        for (File root : roots) {
            System.out.println(root);
        }
    }
}
