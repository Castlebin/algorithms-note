package inner;

/**
 * 2. 局部内部类
 * 局部内部类是定义在一个方法或者一个作用域里面的类，
 * 它和成员内部类的区别在于局部内部类的访问仅限于方法内或者该作用域内
 *
 * 注意: 局部内部类就像是方法里面的一个局部变量一样，
 * 是不能有 public、protected、private 以及 static 修饰符的
 */
public class Man {

    public People getWoman() {
        class Woman extends People {
            String gender = "w";
        }

        return new Woman();
    }

}
