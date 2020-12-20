package algorithm;

import java.util.HashMap;
import java.util.Map;

public class MyLRUCache<K, V> {
    private MyLinkedHashMap<K, V> cache;

    public MyLRUCache(int capacity) {
        this.cache = new MyLinkedHashMap<>(capacity);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public V get(K key) {
        return cache.get(key);
    }

    class MyLinkedHashMap<K, V> {
        private Map<K, V> map;
        private MyLinkedList<K> linkedKeys;
        private int capacity;

        public MyLinkedHashMap(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
            linkedKeys = new MyLinkedList<>();
        }

        public void put(K key, V value) {
            if (map.size() == capacity) {
                K lastKey = linkedKeys.removeLast();
                map.remove(lastKey);
            }
            if (!map.containsKey(key)) {
                linkedKeys.addToHead(key);
            } else {
                linkedKeys.moveToHead(key);
            }
            map.put(key, value);
        }

        public V get(K key) {
            V value = map.get(key);
            if (value != null) {
                linkedKeys.moveToHead(key);
            }
            return value;
        }

        public int size() {
            return map.size();
        }

        class MyLinkedList<T> {
            private Node<T> head;
            private Node<T> tail;
            private int size;

            public void addToHead(T value) {
                Node<T> node = new Node<>();
                node.value = value;
                if (size == 0) {
                    head = node;
                    tail = node;
                } else {
                    node.next = head;
                    head.prev = node;
                    head = node;
                }
                size++;
            }

            public T removeLast() {
                if (size == 0) {
                    return null;
                }
                Node<T> nextTail = tail.prev;
                tail.prev = null;
                Node<T> lastTail = tail;
                tail = nextTail;
                size--;
                return lastTail.value;
            }

            public void moveToHead(T value) {
                if (head == null || value == head.value) {
                    return;
                }
                Node<T> cur = head.next;
                while (cur != null) {
                    if (cur.value == value) {
                        // moveToHead
                        Node<T> prev = cur.prev;
                        Node<T> next = cur.next;

                        if(cur == tail) {
                            tail = tail.prev;
                        }

                        prev.next = next;
                        next.prev = prev;
                        head.prev = cur;
                        cur.next = head;
                        head = cur;
                        break;
                    }
                    cur = cur.next;
                }
            }

            class Node<T> {
                T value;
                Node<T> prev;
                Node<T> next;
            }
        }
    }

}
