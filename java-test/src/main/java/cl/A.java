package cl;

public class A {

    static {
        System.out.println("class A static block...");
        int i = 1/0;
    }

    public static void print() {
        System.out.println("A print.");
    }

    public static void printt() {
        System.out.println("A printt.");
    }

}
