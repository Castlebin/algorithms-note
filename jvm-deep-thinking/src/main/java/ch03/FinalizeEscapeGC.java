package ch03;

/**
 * 3-2 一次对象的自我拯救演示

 output:

     finalize method calling...
     Yes, I'm alive
     No, I'm dead..

 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        saveSelf();// 第一次，成功的拯救自己

        saveSelf();// 对象已经执行过一次finalize方法，不会再次执行
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method calling...");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void isAlive() {
        if (SAVE_HOOK != null) {
            System.out.println("Yes, I'm alive");
        } else {
            System.out.println("No, I'm dead..");
        }
    }

    public static void saveSelf() throws InterruptedException {
        SAVE_HOOK = null;
        System.gc();
        // finalize方法优先级很低，所以暂停0.5毫秒等待它的执行
        Thread.sleep(500);
        isAlive();
    }
}
