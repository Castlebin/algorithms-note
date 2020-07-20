package serialize;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class TestSerialize {
    public static void main(String[] args) {
        StudentCannotBeSerialized zhangsan = new StudentCannotBeSerialized();
        zhangsan.setName("zhangsan");
        zhangsan.setAge(20);

        Student wanger = new Student();
        wanger.setName("wanger");
        wanger.setAge(18);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tmp/wanger"));
            ObjectOutputStream ooszs = new ObjectOutputStream(new FileOutputStream("tmp/zhangsan"));) {
            oos.writeObject(wanger);
            ooszs.writeObject(zhangsan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
