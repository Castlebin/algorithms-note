package ch07;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    public static AtomicReference<User> atomicUserRef = new AtomicReference<>();

    public static void main(String[] args) {
        User user = new User("Conan", 17);
        atomicUserRef.set(user);
        User updateUser = new User("Shinichi", 28);
        atomicUserRef.compareAndSet(user, updateUser);
        System.out.println(atomicUserRef.get().getName());
        System.out.println(atomicUserRef.get().getAge());
    }

    @AllArgsConstructor
    @Getter
    static class User {
        private String name;
        private int age;
    }
}
