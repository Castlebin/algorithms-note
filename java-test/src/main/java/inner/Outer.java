package inner;

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
