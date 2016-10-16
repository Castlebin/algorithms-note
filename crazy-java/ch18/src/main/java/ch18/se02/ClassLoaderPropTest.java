package ch18.se02;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ClassLoaderPropTest {
    public static void main(String[] args) throws IOException {
        // 获取系统类加载器
        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器：" + systemLoader);

        /*获取系统类加载器的加载路径——通常由CLASSPATH环境变量指定
          如果操作系统没有指定CLASSPATH环境变量，默认以当前路径作为
          系统类加载器的加载路径*/
        Enumeration<URL> em1 = systemLoader.getResources("");
        while (em1.hasMoreElements()) {
            System.out.println(em1.nextElement());
        }

        // 获取系统类加载器的父类加载器：得到扩展类加载器
        ClassLoader extensionLader = systemLoader.getParent();
        System.out.println("扩展类加载器：" + extensionLader);
        System.out.println("扩展类加载器的加载路径："
                + System.getProperty("java.ext.dirs"));

        // 此处结果为null，并非根类加载器，是因为根类加载并没有继承ClassLoader抽象类，并且根类加载器并不是Java实现的
        // 而且实际上程序通常也无须访问根类加载器
        System.out.println("扩展类加载器的parent: "
                + extensionLader.getParent());
    }
}
