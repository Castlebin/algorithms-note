package algorithm;

import java.util.*;

/**
 * LRU 缓存
 */
public class LFUCache<K, V> {
    // 缓存 key 的最大容量，超出的话，得做逐出
    int capacity;

    // 缓存 key - value
    private Map<K, V> keyToValMap;
    // 记录 Key 的访问频次
    private Map<K, Integer> keyToFreqMap;
    // 记录频次和缓存 key 的对应关系，同一个频次可能会有多个 key ，freq 可以看做有序
    private TreeMap<Integer, List<K>> freqToKeysMap;

    public LFUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must > 0");
        }
        this.capacity = capacity;
        keyToValMap = new HashMap<>();
        keyToFreqMap = new HashMap<>();
        freqToKeysMap = new TreeMap<>();
    }

    public V get(K key) {
        V value = keyToValMap.get(key);
        // 如果存在，则应该提升 key 的访问频次
        if (value != null) {
            incrementKeyFreq(key);
        }
        return value;
    }

    private void incrementKeyFreq(K key) {
        // 提升频次
        int freq = keyToFreqMap.get(key);
        keyToFreqMap.put(key, freq + 1);

        if (freqToKeysMap.get(freq + 1) == null) {
            freqToKeysMap.put(freq + 1, new LinkedList<K>());
        }
        freqToKeysMap.get(freq + 1).add(key);

        // 从 freqToKeysMap 中 当前频次 中移除该 key ，如果移除后，该 频次 对应的 key list 为空，从 freqToKeysMap 中移除该频次
        freqToKeysMap.get(freq).remove(key);
        if (freqToKeysMap.get(freq).size() == 0) {
            freqToKeysMap.remove(freq);
        }
    }

    public void put(K key, V value) {
        V exist = keyToValMap.get(key);
        if (exist == null) {
            // 说明是新加的一个 key
            // 如果缓存已满，那么先做逐出
            if (keyToValMap.size() >= capacity) {
                removeLeastFreqKey();
            }
            keyToValMap.put(key, value);
            // 新加的 key，记录它的频次
            keyToFreqMap.put(key, 1);
            if (!freqToKeysMap.containsKey(1)) {
                freqToKeysMap.put(1, new LinkedList<K>());
            }
            freqToKeysMap.get(1).add(key);
        } else {
            // 已经存在的 key
            keyToValMap.put(key, value);
            // 提升它的频次
            incrementKeyFreq(key);
        }
    }

    private void removeLeastFreqKey() {
        Integer leastFreq = freqToKeysMap.firstKey();
        K leastFreqOldestKey = freqToKeysMap.get(leastFreq).get(0);
        keyToValMap.remove(leastFreqOldestKey);
        keyToFreqMap.remove(leastFreqOldestKey);
        freqToKeysMap.get(leastFreq).remove(0);
        if (freqToKeysMap.get(leastFreq).size() == 0) {
            freqToKeysMap.remove(leastFreq);
        }
    }

}
