package inner;

/**
 * 1. 成员内部类
 */
public class Outer {
    private Inner inner = null;

    public Inner getInnerInstance() {
        if (inner == null) {
            inner = new Inner();
        }
        return inner;
    }

    class Inner {
    }

}
