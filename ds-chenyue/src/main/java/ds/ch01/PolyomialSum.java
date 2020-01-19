package ds.ch01;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 多项式求和
 * <p>
 * y = a0 + a1 * x + a2 * x^2 + a3 * x^3 + ... + aN * x^N
 */
public class PolyomialSum {

    /**
     * 挨个求和，循环
     */
    public static double polyomialSum_1(double[] aArray, double x) {
        double y = 0;
        for (int i = 0; i < aArray.length; i++) {
            y += aArray[i] * pow(x, i);
        }
        return y;
    }

    private static double pow(double x, double n) {
        double pow = 1;
        for (int i = 0; i < n; i++) {
            pow *= x;
        }
        return pow;
    }

    /**
     * 多项式提取公因子，相乘求和
     */
    public static double polyomialSum_2(double[] aArray, double x) {
        double y = 0;
        for (int i = aArray.length; i > 0; i--) {
            y = y * x + aArray[i - 1];
        }
        return y;
    }

    private static double[] aArray = new double[100];

    @BeforeClass
    public static void buildCoefficient() {
        for (int i = 0; i < aArray.length; i++) {
            aArray[i] = i + 1;
        }
    }

    @Test
    public void testBesic() {
        System.out.println(polyomialSum_1(aArray, 1) + "\t" + polyomialSum_2(aArray, 1));
    }

    @Test
    public void testPolyomialSum() {
        System.out.println(polyomialSum_1(aArray, 1.001) + "\t" + polyomialSum_2(aArray, 1.001));
        System.out.println(polyomialSum_1(aArray, 1.002) + "\t" + polyomialSum_2(aArray, 1.002));
    }

}
