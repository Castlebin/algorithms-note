package ch01.item01;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Services {

    /**
     * 私有的构造器，保证类不会被实例化
     */
    private Services() {}

    private static final Map<String, Provider> providers = new ConcurrentHashMap<>();
    private static final String DEFAULT_PROVIDER = "default";

    public static void registerProvider(String name, Provider p) {
        providers.put(name, p);
    }

    public static void registerDefaultProvider(Provider p) {
        providers.put(DEFAULT_PROVIDER, p);
    }

    public static Service newInstance(String name) {
        Provider p = providers.get(name);
        if (p == null) {
            throw new IllegalArgumentException("No provider registered with name: " + name);
        }
        return p.newService();
    }

    public static Service newInstance() {
        return newInstance(DEFAULT_PROVIDER);
    }

}
