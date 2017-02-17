package ch02.item01;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// No.1 考虑使用静态工厂方法代替构造器
public class Services {
    private static final Map<String, Provider> providers = new ConcurrentHashMap<>();

    public static final String DEFAULT_PROVIDER_NAME = "<def>";

    private Services() {} // private构造器 不可实例化

    // provider registration api
    public static void registerDefaultProvider(Provider p) {
        providers.put(DEFAULT_PROVIDER_NAME, p);
    }

    public static void registerProvider(String name, Provider p) {
        providers.put(name, p);
    }

    // Service access api
    public static Service newInstance() {
        return newInstance(DEFAULT_PROVIDER_NAME);
    }

    // 静态工厂方法 替代了构造器
    public static Service newInstance(String name) {
        Provider p = providers.get(name);
        if (p == null) {
            throw new IllegalArgumentException("No provider registered with name: " + name);
        }

        return p.newService();
    }

}
