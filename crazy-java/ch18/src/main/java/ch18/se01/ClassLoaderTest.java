package ch18.se01;

class Tester {
    static {
        System.out.println("Tester类型的静态初始化块执行…");
    }
}

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        // ClassLoader类的loadClass()仅仅只会加载类，JVM不会进行类初始化
        cl.loadClass("ch18.se01.Tester");

        System.out.println("-------------------");

        // Class.forName() JVM会去执行类初始化
        Class.forName("ch18.se01.Tester");
    }
}
