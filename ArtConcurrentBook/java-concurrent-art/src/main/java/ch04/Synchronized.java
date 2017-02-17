package ch04;

// 可使用命令：javap -v Synchronized.class，查看编译后的字节码文件内容
public class Synchronized {
    public static void main(String[] args) {
        synchronized (Synchronized.class) {}
        m();
    }
    public static synchronized void m() {}
}
