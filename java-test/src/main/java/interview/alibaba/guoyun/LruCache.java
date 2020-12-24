package interview.alibaba.guoyun;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 设计并实现一个简易的缓存框架，要求支持并发的读写和LRU缓存淘汰算法，并考虑性能
 * @author Lgy
 * @desc 简单缓存
 * @date 2020-12-19
 */
public class LruCache<K,V> extends LinkedHashMap<K,V> {
    private final int maxCapacity;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private final Lock lock = new ReentrantLock();

    public LruCache(int maxCapacity) {
        super(16,DEFAULT_LOAD_FACTOR, true);
        this.maxCapacity = maxCapacity;
    }
    public LruCache(int initialCapacity, int maxCapacity) {
        super(initialCapacity,DEFAULT_LOAD_FACTOR, true);
        this.maxCapacity = maxCapacity;
    }
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }
    @Override
    public boolean containsKey(Object key) {
        try {
            lock.lock();
            return super.containsKey(key);
        } finally {
            lock.unlock();
        }
    }


    @Override
    public V get(Object key) {
        try {
            lock.lock();
            return super.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V put(K key, V value) {
        try {
            lock.lock();
            return super.put(key, value);
        } finally {
            lock.unlock();
        }
    }
    @Override
    public int size() {
        try {
            lock.lock();
            return super.size();
        } finally {
            lock.unlock();
        }
    }
    @Override
    public void clear() {
        try {
            lock.lock();
            super.clear();
        } finally {
            lock.unlock();
        }
    }

    public List<Map.Entry<K, V>> getAll() {
        try {
            lock.lock();
            return new ArrayList<>(super.entrySet());
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final LruCache<String,Integer> cache = new LruCache<String, Integer>(16);
        final String[] keys = {"张三","李四","王五","赵六"};
        final Random random = new Random();
        for(int i=0;i<50;i++){
            String key = keys[random.nextInt(4)];
            cache.put(key,i);
            System.out.println("设置："+key+"--->"+i);
        }
        System.out.println("=============初始化设置的最终结果=====================");
        for (Map.Entry<String, Integer> entry : cache.getAll()) {
            System.out.println(entry.getKey()+"-->"+entry.getValue());
        }

        for(int i=0;i<16;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<50;i++){
                        String key = keys[random.nextInt(4)];
                        cache.put(key,i+100);
                        System.out.println(Thread.currentThread().getName()+"设置："+key+"--->"+(i+100));
                    }
                }
            },"Provider-"+i).start();
        }

        for(int i=0;i<2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("=====================================================================");
                    for(int i=0;i<50;i++){
                        String key = keys[random.nextInt(4)];
                        System.out.println(Thread.currentThread().getName()+"获取："+key +"-->"+cache.get(key));
                    }
                }
            },"Consumer-"+i).start();
        }

    }
   
}
