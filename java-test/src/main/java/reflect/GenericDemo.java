package reflect;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

/**
 * 反射能够在运行时获取到 类的泛型信息
 */
public class GenericDemo {

    /**
     * 定义一个包含泛型的类
     * @param <U>
     * @param <V>
     */
    static class GenericTest<U extends Comparable<U>, V> {
        U u;
        V v;
        List<String> list;

        public U test(List<? extends Number> numbers) {
            return null;
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        Class<GenericTest> cls = GenericTest.class;
        // 获取 类的类型参数
        for (TypeVariable<Class<GenericTest>> typeParameter : cls.getTypeParameters()) {
            System.out.println(typeParameter.getName() + " extends " + Arrays.toString(typeParameter.getBounds()));
        }
        // 上面打印出 以下信息
        // U extends [java.lang.Comparable<U>]
        // V extends [class java.lang.Object]


        // 字段 泛型类型
        Field fu = cls.getDeclaredField("u");
        System.out.println(fu.getGenericType());    // U

        // 字段 参数化类型
        Field flist = cls.getDeclaredField("list");
        Type listType = flist.getGenericType();
        if (listType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) listType;
            System.out.println("Field list raw type: " + pType.getRawType()
                + " ,type arguments: " + Arrays.toString(pType.getActualTypeArguments()));
            // Field list raw type: interface java.util.List ,type arguments: [class java.lang.String]
        }

        //方法的泛型参数
        Method mTest = cls.getMethod("test", new Class[] { List.class });
        for(Type type : mTest.getGenericParameterTypes()) {
            System.out.println(type);
        }
        // java.util.List<? extends java.lang.Number>
    }

}
