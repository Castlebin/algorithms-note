package serialize;

import java.io.*;

public class StudentExternal implements Externalizable {
//    private static final long serialVersionUID = 1L;

    private String name;
    private int age;

    public static String pre = "沉默";
    transient String meizi = "王三";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pre=" + pre +
                ", meizi='" + meizi + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }

}
