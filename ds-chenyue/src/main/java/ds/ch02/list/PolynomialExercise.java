package ds.ch02.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 02-线性结构2 一元多项式的乘法与加法运算 (20分)
 * 设计函数分别求两个一元多项式的乘积与和。
 *
 * 输入格式:
 * 输入分2行，每行分别先给出多项式非零项的个数，再以指数递降方式输入一个多项式非零项系数和指数（绝对值均为不超过1000的整数）。数字间以空格分隔。
 *
 * 输出格式:
 * 输出分2行，分别以指数递降方式输出乘积多项式以及和多项式非零项的系数和指数。数字间以空格分隔，但结尾不能有多余空格。零多项式应输出0 0。
 *
 * 输入样例:
 * 4 3 4 -5 2  6 1  -2 0
 * 3 5 20  -7 4  3 1
 *
 *
 *
 * 输出样例:
 * 15 24 -25 22 30 21 -10 20 -21 8 35 6 -33 5 14 4 -15 3 18 2 -6 1
 * 5 20 -4 4 -5 2 9 1 -2 0
 */
public class PolynomialExercise {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String secondLine = sc.nextLine();

        List<PolynomialItem> a = parseLineToPoly(firstLine);
        List<PolynomialItem> b = parseLineToPoly(secondLine);

        print(multiply(a, b));
        print(add(a, b));
    }

    private static List<PolynomialItem> parseLineToPoly(String line) {
        List<PolynomialItem> poly = new ArrayList<>();
        String[] nums = line.split("\\s+");
        int size = Integer.parseInt(nums[0]);
        for (int i = 1; i < 1 + 2 * size; i += 2) {
            poly.add(new PolynomialItem(Integer.parseInt(nums[i]), Integer.parseInt(nums[i+1])));
        }
        return poly;
    }

    public static List<PolynomialItem> add(List<PolynomialItem> a, List<PolynomialItem> b) {
        List<PolynomialItem> result = new ArrayList<>();
        int posA = 0, posB = 0;
        while (posA < a.size() && posB < b.size()) {
            PolynomialItem pa = a.get(posA);
            PolynomialItem pb = b.get(posB);
            if (pa.exponent > pb.exponent) {
                result.add(pa);
                posA++;
            } else if (pa.exponent < pb.exponent) {
                result.add(pb);
                posB++;
            } else {
                if (pa.coefficient + pb.coefficient != 0) {
                    result.add(new PolynomialItem(pa.coefficient + pb.coefficient, pa.exponent));
                }
                posA++;
                posB++;
            }
        }
        if (posA < a.size()) {
            for (int i = posA; i < a.size(); i++) {
                result.add(a.get(i));
            }
        } else if (posB < b.size()) {
            for (int i = posB; i < b.size(); i++) {
                result.add(b.get(i));
            }
        }
        return result;
    }

    public static List<PolynomialItem> multiply(List<PolynomialItem> a, List<PolynomialItem> b) {
        List<PolynomialItem> result = new ArrayList<>();
        if (a.size() == 0 || b.size() == 0) {
            return result;
        }
        List<List<PolynomialItem>> multi = new ArrayList<>();
        for (PolynomialItem pa : a) {
            List<PolynomialItem> mu = new ArrayList<>();
            for (PolynomialItem pb : b) {
                mu.add(new PolynomialItem(pa.coefficient * pb.coefficient, pa.exponent + pb.exponent));
            }
            multi.add(mu);
        }

        for (List<PolynomialItem> mu : multi) {
            result = add(result, mu);
        }

        return result;
    }

    static class PolynomialItem {
        int coefficient;
        int exponent;

        public PolynomialItem(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }
    }

    public static void print(List<PolynomialItem> polynomial) {
        if (polynomial.size() == 0) {
            System.out.println(0 + " " + 0);
        } else {
            StringBuilder sb = new StringBuilder(polynomial.get(0).coefficient + " " + polynomial.get(0).exponent);
            for (int i = 1; i < polynomial.size(); i++) {
                sb.append(" " + polynomial.get(i).coefficient + " " + polynomial.get(i).exponent);
            }
            System.out.println(sb.toString());
        }
    }

}