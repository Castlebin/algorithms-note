package ch12;

/**
 * 在 64 位系统下
 * 对 long 或者 double 类型的普通变量 （不加 volatile） 的读写操作
 * 可能被分为两次 32 位操作，验证这条理论在实际的java虚拟机上的运行情况
 *
 * (不太明白，都加了 volatile ，仍然一样（因为上个版本判断条件不是原子性的啊）)
 * 
 * 以上只是理论，经修改程序，发现，在现有的商用虚拟机上，对 64 位的 long、double 变量的读写，均能保证原子性
 * （深入理解Java虚拟机 书中 对此的描述是对的，标注的引用文章里专门做了实验）
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
             * 程序如果退出，说明64位变量确实会被拆分为两次操作
             */
            // 这里对 num 做一次 复制，保证判断时的数据一致性（因为下面的判断是一个复合操作）
            // 否则，即使程序 break， 也并不能说明 num 处于一个临界状态
            long n = num;
            if (n != num1 && n != num2) {
                System.out.println("经过第 " + count + " 轮运行发现：n=" + n);
                System.out.println("long 或者 double类型 64位变量的读写可能会被分为两次32位操作");
                break;
            }
        }
    }
}
