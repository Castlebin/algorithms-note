package serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestSerialize {
    public static void main(String[] args) {
        StudentCannotBeSerialized zhangsan = new StudentCannotBeSerialized();
        zhangsan.setName("zhangsan");
        zhangsan.setAge(20);

        Student wanger = new Student();
        wanger.setName("wanger");
        wanger.setAge(18);
        wanger.meizi = "王妮";
        wanger.pre = "ainio";
        System.out.println(wanger);

        StudentExternal lisi = new StudentExternal();
        lisi.setName("李四");
        lisi.setAge(18);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tmp/wanger"));
            ObjectOutputStream ooszs = new ObjectOutputStream(new FileOutputStream("tmp/zhangsan"));
            ObjectOutputStream oosls = new ObjectOutputStream(new FileOutputStream("tmp/lisi"));) {
            oos.writeObject(wanger);
            ooszs.writeObject(zhangsan);
            oosls.writeObject(lisi);
        } catch (Exception e) {
            e.printStackTrace();
        }

        wanger.pre = "Hi Hi Hi";

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tmp/wanger"));
            ObjectInputStream oiszs = new ObjectInputStream(new FileInputStream("tmp/zhangsan"));
            ObjectInputStream oisls = new ObjectInputStream(new FileInputStream("tmp/lisi"))) {
            Student student = (Student) ois.readObject();
            System.out.println(student);

            StudentExternal ls = (StudentExternal)oisls.readObject();
            System.out.println(ls);

            oiszs.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
