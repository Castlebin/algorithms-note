package ch08;

public interface Animal {
    void move();

    default void love() {
        System.out.println("love you!");
    }
}

class Bird implements Animal {

    @Override
    public void move() {
        System.out.println("Birds fly");
    }

}

class Fish implements Animal {

    @Override
    public void move() {
        System.out.println("Fish swim");
    }

}
