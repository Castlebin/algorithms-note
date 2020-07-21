package rpc;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (Socket socket = new Socket("localhost", 1110);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {
            oos.writeObject("rpc.api.HelloService");
            oos.writeObject("sayHello");
            oos.writeObject(new Object[]{String.class});
            oos.writeObject(new Object[]{"LiLei"});

            Object result = ois.readObject();
            System.out.println("rpc result: " + result);
        }
    }

}
