package ru.job4j.collection.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);
    boolean hasNull = false;

    @Override
    public boolean add(T value) {
        if (this.contains(value)) {
            return false;
        }
        set.add(value);
        if (value == null) {
            hasNull = true;
        }
        return true;
    }

    @Override
    public boolean contains(T value) {
        if (value == null) {
            return hasNull;
        }
        Iterator<T> iter = set.iterator();
        while (iter.hasNext()) {
            T item = iter.next();
            if (value.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}