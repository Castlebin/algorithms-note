package rpc;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try (Socket socket = new Socket("localhost", 1110);
             OutputStream os = socket.getOutputStream();
             InputStream is = socket.getInputStream();
             ObjectOutputStream oos = new ObjectOutputStream(os);
             ObjectInputStream ois = new ObjectInputStream(is);) {
            oos.writeObject("rpc.api.HelloService");
            oos.writeObject("sayHello");
            oos.writeObject(new Object[]{String.class});
            oos.writeObject(new Object[]{"LiLei"});

            Object result = ois.readObject();
            System.out.println("rpc result: " + result);
        }
    }

}
