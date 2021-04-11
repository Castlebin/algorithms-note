package algorithm;

import java.util.HashMap;
import java.util.LinkedList;

public class LRUCache<K, V> {

    private int capacity;
    private LinkedList<K> linkedKeys;
    private HashMap<K, V> keyToVal;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must > 0");
        }
        this.capacity = capacity;
        linkedKeys = new LinkedList<>();
        keyToVal = new HashMap<>(capacity);
    }

    public void put(K key, V value) {
        // 更新还是插入
        V oldValue = keyToVal.get(key);
        if (oldValue == null) {
            // 插入，插入前检查容量是否已满，满了的话需要做逐出
            if (keyToVal.size() == capacity) {
                // 逐出
                K remove = linkedKeys.removeLast();
                keyToVal.remove(remove);
            }
            keyToVal.put(key, value);
            // 新插入的元素放到头部
            linkedKeys.addFirst(key);
        } else {
            // 更新
            keyToVal.put(key, value);
            // 访问到的元素放到头部
            moveKeyToHead(key);
        }
    }

    public V getOrDefault(K key, V defaultValue) {
        V v = get(key);
        return v == null? defaultValue : v;
    }

    public V get(K key) {
        V value = keyToVal.get(key);
        if (value != null) {
            // 移动到头部
            moveKeyToHead(key);
        }
        return value;
    }

    private void moveKeyToHead(K key) {
        linkedKeys.remove(key);
        linkedKeys.addFirst(key);
    }

}
