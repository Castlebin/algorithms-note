package ch07.se02;

public class RuntimeTest {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("处理器数量：" + runtime.availableProcessors());
        System.out.println("空闲内存：" + runtime.freeMemory());
        System.out.println("总内存：" + runtime.totalMemory());
        System.out.println("可用最大内存：" + runtime.maxMemory());
    }
}
