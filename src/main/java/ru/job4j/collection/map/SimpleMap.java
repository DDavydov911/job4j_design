package ru.job4j.collection.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> pairs : table) {
            if (pairs != null) {
                int i = indexFor(hash(Objects.hashCode(pairs.key)));
                if (newTable[i] == null) {
                    newTable[i] = pairs;
                } else {
                    System.out.println("Ячейчка занята, а коллизию разрешать не требуется :(");
                }
            }
        }
        table = newTable;
        modCount++;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int point = 0;
            final int counter = modCount;
            @Override
            public boolean hasNext() {
                if (point < table.length && table[point] != null) {
                    return true;
                }
                for (int i = point; i < table.length; i++) {
                    if (table[i] != null) {
                        point = i;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (counter != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (K) table[point++];
            }
        };
    }

    @Override
    public boolean put(K key, V value) {
        if (count == capacity * LOAD_FACTOR) {
            expand();
        }
        int i = indexFor(hash(key.hashCode()));
        if (table[i] == null) {
            table[i] = new MapEntry(key, value);
            count++;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(Objects.hashCode(key)));
        if (table[i] == null) {
            return  null;
        } else {
            return table[i].value;
        }
    }

    @Override
    public boolean remove(K key) {
        int i = indexFor(hash(Objects.hashCode(key)));
        if (table[i] != null && table[i].key.equals(key)) {
            table[i] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Map map = new SimpleMap();
        map.put("Key1", "Value1");
        map.put("Key2", "Value2");
        map.put("Key3", "Value3");
        int hashCode1 = Objects.hashCode("Key1");
        int hashCode2 = Objects.hashCode("Key2");
        int hashCode3 = Objects.hashCode("Key3");
        int index1 = (hashCode1 ^ hashCode1 >>> 16) & 7;
        int index2 = (hashCode2 ^ hashCode2 >>> 16) & 7;
        int index3 = (hashCode3 ^ hashCode3 >>> 16) & 7;
        System.out.println(index1);
        System.out.println(index2);
        System.out.println(index3);
        System.out.println(map.get("Key3"));
        System.out.println(map.put("Key1", "Value1"));
    }
}