package common;

public class TimeUtil {

    /**
     * 运行单次，计算函数运行时间
     * @param runnable 函数
     * @return
     */
    public static long timeCost(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - start;
    }

    /**
     * 重复运行多次，计算出函数运行平均时间
     * @param runnable 函数（因为要跑多次，所以函数必须是无状态的）
     * @param n 次数
     * @return
     */
    public static double timeCostN(Runnable runnable, int n) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            runnable.run();
        }
        return (System.currentTimeMillis() - start) * 1.0 / n;
    }

}
