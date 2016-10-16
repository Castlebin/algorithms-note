package ch18.se02;

import java.net.URL;

public class BootstrapTest {
    public static void main(String[] args) {
        // 获取根类加载器所加载的全部URL
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toString());
        }
    }
}
