package ds.ch02.linkedlist;

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
 *
 * 链表实现
 */
public class PolynomialExerciseLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String secondLine = sc.nextLine();

        PolynomialNode a = parseLineToPoly(firstLine);
        PolynomialNode b = parseLineToPoly(secondLine);

        print(multiply(a, b));
        print(add(a, b));
    }

    private static PolynomialNode parseLineToPoly(String line) {
        PolynomialNode head = new PolynomialNode();
        String[] nums = line.split("\\s+");
        int size = Integer.parseInt(nums[0]);
        if (size < 0) {
            throw new RuntimeException("长度不能小于0");
        }
        if (size == 0) {
            return null;
        }
        head.data = new PolynomialItem(Integer.parseInt(nums[1]), Integer.parseInt(nums[2]));
        PolynomialNode cur = head;
        for (int i = 3; i < 1 + 2 * size; i += 2) {
            PolynomialNode next = new PolynomialNode();
            next.data = new PolynomialItem(Integer.parseInt(nums[i]), Integer.parseInt(nums[i+1]));
            cur.next = next;
            cur = next;
        }
        return head;
    }

    public static PolynomialNode add(PolynomialNode a, PolynomialNode b) {
        if (a == null && b == null) {
            return null;
        }
        PolynomialNode posA = a;
        PolynomialNode posB = b;
        PolynomialNode front = new PolynomialNode();// 前导节点
        PolynomialNode prev = front;
        while (posA != null && posB != null) {
            PolynomialItem pa = posA.data;
            PolynomialItem pb = posB.data;
            if (pa.exponent > pb.exponent) {
                PolynomialNode cur = new PolynomialNode();
                cur.data = new PolynomialItem(pa.coefficient, pa.exponent);
                prev.next = cur;
                prev = cur;
                posA = posA.next;
            } else if (pa.exponent < pb.exponent) {
                PolynomialNode cur = new PolynomialNode();
                cur.data = new PolynomialItem(pb.coefficient, pb.exponent);
                prev.next = cur;
                prev = cur;
                posB = posB.next;
            } else {
                if (pa.coefficient + pb.coefficient != 0) {
                    PolynomialNode cur = new PolynomialNode();
                    cur.data = new PolynomialItem(pa.coefficient + pb.coefficient, pa.exponent);
                    prev.next = cur;
                    prev = cur;
                }
                posA = posA.next;
                posB = posB.next;
            }
        }
        if (posA != null) {
            while (posA != null) {
                PolynomialItem pa = posA.data;
                PolynomialNode cur = new PolynomialNode();
                cur.data = new PolynomialItem(pa.coefficient, pa.exponent);
                prev.next = cur;
                prev = cur;
                posA = posA.next;
            }
        } else if (posB != null) {
            while (posB != null) {
                PolynomialItem pb = posB.data;
                PolynomialNode cur = new PolynomialNode();
                cur.data = new PolynomialItem(pb.coefficient, pb.exponent);
                prev.next = cur;
                prev = cur;
                posB = posB.next;
            }
        }
        return front.next;
    }

    public static PolynomialNode multiply(PolynomialNode a, PolynomialNode b) {
        if (a == null || b == null) {
            return null;
        }
        List<PolynomialNode> multi = new ArrayList<>();
        PolynomialNode posA = a;
        while (posA != null) {
            PolynomialNode mu = null;
            PolynomialNode posB = b;
            while (posB != null) {
                PolynomialNode cur = new PolynomialNode();
                cur.data = new PolynomialItem(posA.data.coefficient * posB.data.coefficient,
                        posA.data.exponent + posB.data.exponent);
                mu = add(mu, cur);
                posB = posB.next;
            }
            posA = posA.next;
            multi.add(mu);
        }

        PolynomialNode head = null;
        for (PolynomialNode mu : multi) {
            head = add(head, mu);
        }

        return head;
    }

    static class PolynomialItem {
        int coefficient;
        int exponent;

        public PolynomialItem(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }
    }

    static class PolynomialNode {
        PolynomialItem data;
        PolynomialNode next;
    }

    public static void print(PolynomialNode polynomial) {
        if (polynomial == null || polynomial.data == null) {
            System.out.println(0 + " " + 0);
        } else {
            StringBuilder sb = new StringBuilder(polynomial.data.coefficient + " " + polynomial.data.exponent);
            PolynomialNode next = polynomial.next;
            while (next != null && next.data != null) {
                sb.append(" " + next.data.coefficient + " " + next.data.exponent);
                next = next.next;
            }
            System.out.println(sb.toString());
        }
    }

}