package ch12;

/**
 * （即使在 64 位系统下）
 * 对 long 或者 double 类型的普通变量 （不加 volatile） 的读写操作
 * 仍可能被分为两次 32 位操作
 *
 * (不太明白，都加了 volatile ，仍然一样)
 */
public class LongMultiThreadWriteReadTest {

    private static long num1 = 1;
    private static long num2 = -1;

    private static long num = num1;

    public static void main(String[] args) {
        int count = 0;
        while (true) {
            count++;

            new Thread(() -> num = num2).start();
            new Thread(() -> num = num1).start();

            /**
             * 程序退出，说明64位变量确实会被拆分为两次操作
             */
            if (num != num1 && num != num2) {
                System.out.println("经过第 " + count + " 轮运行发现：");
                System.out.println("long 或者 double类型 64位变量的读写可能会被分为两次32位操作");
                break;
            }
        }
    }
}
