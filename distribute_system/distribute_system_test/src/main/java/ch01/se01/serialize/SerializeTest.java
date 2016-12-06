package ch01.se01.serialize;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.*;

public class SerializeTest {
    public static void main(String[] args) {
        Person xiaoming = new Person("xiaoming", 18);

        // java 本身的序列化反序列化机制还有 hessian都要求要序列化的对象的类实现Serializable接口
        javaSerialize(xiaoming);

        hessianSerialize(xiaoming);
    }

    private static <T extends Serializable> void javaSerialize(T serializable) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(os)) {
            out.writeObject(serializable);
            byte[] xiaomingBytes = os.toByteArray();

            // 执行反序列化
            try (ByteArrayInputStream is = new ByteArrayInputStream(xiaomingBytes);
                 ObjectInputStream in = new ObjectInputStream(is)) {
                T readObject = (T) in.readObject();
                System.out.println(readObject);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <T extends Serializable> void hessianSerialize(T serializable) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            HessianOutput out = new HessianOutput(os);
            out.writeObject(serializable);
            byte[] xiaomingBytes = os.toByteArray();

            // 执行反序列化
            try (ByteArrayInputStream is = new ByteArrayInputStream(xiaomingBytes)) {
                HessianInput in = new HessianInput(is);
                T readObject = (T) in.readObject();
                System.out.println(readObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
