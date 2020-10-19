package ch08;

/**
 * 接口方法，都使用 invokeinterface 方式调用，运行时确定调用版本
 * （包括 java 8 引入的接口默认方法）
 */
public class InvokeInterfaceTest {
    public static void main(String[] args) {
        Animal bird = new Bird();
        bird.move();

        Animal fish = new Fish();
        fish.move();

        fish.love();
    }
}
