package ch02;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * JDK 8 动态生成类导致方法区溢出（使用CGLib）
 * VM args:
       -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m

output:
 Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
     at net.sf.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:348)
     at net.sf.cglib.proxy.Enhancer.generate(Enhancer.java:492)
     at net.sf.cglib.core.AbstractClassGenerator$ClassLoaderData.get(AbstractClassGenerator.java:117)
     at net.sf.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:294)
     at net.sf.cglib.proxy.Enhancer.createHelper(Enhancer.java:480)
     at net.sf.cglib.proxy.Enhancer.create(Enhancer.java:305)
     at ch02.JavaMethodAreaOOM.main(JavaMethodAreaOOM.java:26)
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject {

    }
}
