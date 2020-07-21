package rpc;

import rpc.api.HelloService;
import rpc.impl.HelloServiceImpl;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static final Map<Class<?>, Object> serviceImplMap = new HashMap<>();

    static {
        serviceImplMap.put(HelloService.class, new HelloServiceImpl());
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1110);
        while (true) {
            try(Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());) {

                String serviceClassName = (String)ois.readObject();
                String methodName = (String)ois.readObject();
                Object pts = ois.readObject();
                Object ps = ois.readObject();

                Class<?>[] parameterTypes = new Class[((Object[]) pts).length];
                for (int i = 0; i < ((Object[]) pts).length; i++) {
                    parameterTypes[i] = (Class<?>)((Object[])pts)[i];
                }
                Object[] parameters = new Object[((Object[])ps).length];
                for (int i = 0; i < ((Object[]) ps).length; i++) {
                    parameters[i] = ((Object[])ps)[i];
                }

                Class<?> serviceClass = Class.forName(serviceClassName);
                Object serviceImpl = serviceImplMap.get(serviceClass);
                Method method = serviceImpl.getClass().getMethod(methodName, parameterTypes);

                Object result = method.invoke(serviceImpl, parameters);

                oos.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
