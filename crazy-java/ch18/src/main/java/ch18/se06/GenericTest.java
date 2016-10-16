package ch18.se06;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class GenericTest {
    private Map<String, Integer> score;

    public static void main(String[] args) throws NoSuchFieldException {
        Class<GenericTest> clazz = GenericTest.class;
        Field f = clazz.getDeclaredField("score");

        // 直接使用getType()取出的类型只能获取到原始类型，也就是只能对普通类型的成员变量才准确
        Class<?> a = f.getType();
        System.out.println("score的类型为：" + a);

        // 获取成员变量的泛型类型，使用getGenericType()方法
        Type gType = f.getGenericType();
        // 如果gType确实是泛型类型, 强转
        if (gType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType)gType;
            // 获取其原始类型
            Type rawType = pType.getRawType();
            System.out.println("原始类型是：" + rawType);
            // 获取泛型类型的泛型参数
            Type[] tArgs = pType.getActualTypeArguments();
            System.out.println("泛型信息：");
            int i = 1;
            for (Type type : tArgs) {
                System.out.println("第" + (i++) + "个泛型参数类型是：" + type);
            }
        }
    }
}
