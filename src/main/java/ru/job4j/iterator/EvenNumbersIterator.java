package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    int point = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = point; i < data.length; i++) {
           if (data[i] % 2 == 0) {
               point = i;
               return true;
           }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }

    @Override
    public void remove() {
        Iterator.super.remove();
        point--;
    }

    @Override
    public void forEachRemaining(Consumer<? super Integer> action) {
        Iterator.super.forEachRemaining(action);
    }
}
