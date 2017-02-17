package ch07;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    // 创建一个User类的age属性的更新器
    private static AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    public static void main(String[] args) {
        User conan = new User("conan", 17);
        System.out.println(updater.getAndIncrement(conan));
        System.out.println(updater.get(conan));
    }

    @Getter
    @AllArgsConstructor
    static class User {
        private String name;
        public volatile int age;// 更新器对应的属性必须是public volatile修饰的
    }
}
