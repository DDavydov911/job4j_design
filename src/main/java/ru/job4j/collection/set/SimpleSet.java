package ru.job4j.collection.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        if (this.contains(value)) {
            return false;
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        Iterator<T> iter = set.iterator();
        while (iter.hasNext()) {
            T item = iter.next();
            if (Objects.equals(value, item)) {
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