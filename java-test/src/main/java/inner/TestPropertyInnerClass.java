package inner;

import org.junit.Test;

/**
 * 1. 成员内部类
 */
public class TestPropertyInnerClass {

    /**
     * 成员内部类是依附外部类而存在的，
     * 也就是说，如果要创建成员内部类的对象，前提是必须存在一个外部类的对象
     */
    @Test
    public void test() {
        //第一种方式：
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();   // 必须通过一个Outter对象来创建

        // 第二种形式
        Outer.Inner inner1 = outer.getInnerInstance();
    }

    /**
     * 内部类可以拥有 private 访问权限、protected 访问权限、public 访问权限及包访问权限
     * (由于成员内部类看起来像是外部类的一个成员，所以可以像类的成员一样拥有多种权限修饰)
     *
     * 外部类只能被 public 和包访问两种权限修饰
     */

}
