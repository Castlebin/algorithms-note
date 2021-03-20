package algorithm;

import java.util.*;

/**
 * LFU 缓存
 */
public class LFUCache<K, V> {

    private int capacity;

    /** 存放 缓存 key-value */
    private Map<K, V> keyValueMap;

    /** 存放 缓存 key 的访问频率对应关系 */
    private Map<K, Integer> keyFreqMap;
    /** 存放 访问频率 和 key 对应关系 */
    private Map<Integer, List<K>> freqKeysMap;
    /** 存放当前最小的访问频率 */
    private int minFreq;

    public LFUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must >= 1");
        }
        this.capacity = capacity;
        keyValueMap = new HashMap<>();
        keyFreqMap = new HashMap<>();
        freqKeysMap = new HashMap<>();
    }

    public V get(K key) {
        V value = keyValueMap.get(key);
        if (value != null) {
            // key 存在，需要更新 key 的访问频率
            increaseKeyFreq(key);
        }
        return value;
    }

    private void increaseKeyFreq(K key) {
        int freq = keyFreqMap.get(key);
        keyFreqMap.put(key, freq + 1);
        if (freqKeysMap.get(freq + 1) == null) {
            freqKeysMap.put(freq + 1, new ArrayList<>());
        }
        freqKeysMap.get(freq + 1).add(key);

        freqKeysMap.get(freq).remove(key);
        if (freqKeysMap.get(freq).size() == 0) {
            freqKeysMap.remove(freq);
            if (freq == minFreq) {
                minFreq++;
            }
        }
    }

    public void put(K key, V value) {
        V oldValue = keyValueMap.get(key);
        if (oldValue != null) {
            // 更新旧值，更新访问频率
            keyValueMap.put(key, value);
            increaseKeyFreq(key);
        } else {
            // 新增一个 key
            // 先要检查缓存是否已满，满的话，需要做逐出
            if (keyValueMap.size() == capacity) {
                removeLeaseFreqKey();
            }
            keyValueMap.put(key, value);
            keyFreqMap.put(key, 1);
            if (freqKeysMap.get(1) == null) {
                freqKeysMap.put(1, new ArrayList<>());
            }
            freqKeysMap.get(1).add(key);
            minFreq = 1;
        }
    }

    private void removeLeaseFreqKey() {
        K leaseFreqKey = freqKeysMap.get(minFreq).get(0);
        keyValueMap.remove(leaseFreqKey);
        keyFreqMap.remove(leaseFreqKey);
        freqKeysMap.get(minFreq).remove(0);
        if (freqKeysMap.get(minFreq).size() == 0) {
            freqKeysMap.remove(minFreq);
        }
    }

}
