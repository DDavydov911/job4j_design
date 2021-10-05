package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    private void grow() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, this.size());
        T oldE = container[index];
        container[index] = newValue;
        modCount++;
        return oldE;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, this.size());
        T removedE = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        size--;
        modCount++;
        return removedE;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, this.size());
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int cursor;
            int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[cursor++];
            }
        };
    }
}