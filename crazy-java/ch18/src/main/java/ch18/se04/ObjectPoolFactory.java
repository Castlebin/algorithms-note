package ch18.se04;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ObjectPoolFactory {

    // 定义一个对象池，Key为对象名称，value为实际的对象
    private Map<String, Object> objectPool = new HashMap<>();

    // 根据类名 生成一个对应的实例
    private Object createObject(String clazzName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(clazzName);
        return clazz.newInstance();
    }

    private void initPool(String fileName) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        try (
            FileInputStream fis = new FileInputStream(fileName);
        ) {
            Properties props = new Properties();
            props.load(fis);

            for (String name : props.stringPropertyNames()) {
                objectPool.put(name, createObject(props.getProperty(name)));
            }
        } catch (IOException e) {
            System.out.println("读取文件" + fileName + "发生异常");
            e.printStackTrace();
        }
    }

    public Object getObject(String name) {
        return objectPool.get(name);
    }

    public static void main(String[] args) throws Exception {
        ObjectPoolFactory objectPoolFactory = new ObjectPoolFactory();
        objectPoolFactory.initPool("./ch18/18.4_objectPool.txt");
        System.out.println(objectPoolFactory.getObject("a"));
        System.out.println(objectPoolFactory.getObject("b"));
    }

}
