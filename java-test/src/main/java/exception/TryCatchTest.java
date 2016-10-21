package exception;

// 理解try-catch-finally机制，finally一定会被执行
// 当然，除非jvm被强制中止
public class TryCatchTest {
    public static void main(String[] args) {
        // 输出 为：
        /** try
            catch
            finally
            2
         */
        System.out.println(new TryCatchTest().testTryFinally());


        System.out.println(new TryCatchTest().testTryFinally2());
    }

    int testTryFinally() {
        int i = 0;
        try {
            System.out.println("try ");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("catch");
            return 1;
        } finally {
            System.out.println("finally");
            return 2;
        }
    }

    int testTryFinally2() {
        int i = 0;
        try {
            System.out.println("try ");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("catch");
            System.exit(0);// JVM被强制中止了，finally块就不会执行了
            return 1;
        } finally {
            System.out.println("finally");
            return 2;
        }
    }
}
