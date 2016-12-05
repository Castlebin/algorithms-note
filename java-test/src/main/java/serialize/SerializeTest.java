package serialize;

import java.io.*;

public class SerializeTest {
    public static void main(String[] args) {
        Person xiaoming = new Person("xiaoming", 18);

        try (ByteArrayOutputStream os = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(os)) {
            out.writeObject(xiaoming);
            byte[] xiaomingBytes = os.toByteArray();

            // 执行反序列化
            try (ByteArrayInputStream is = new ByteArrayInputStream(xiaomingBytes);
                 ObjectInputStream in = new ObjectInputStream(is)) {
                Person p = (Person) in.readObject();
                System.out.println(p);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
