package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int countIn;
    int countOut;

    public void push(T value) {
        while (countOut > 0) {
            in.push(out.pop());
            countOut--;
            countIn++;
        }
        countIn++;
        in.push(value);
    }

    public T poll() {
        if (countIn == 0 && countOut == 0) {
            throw new NoSuchElementException();
        }
        while (countIn > 0) {
            out.push(in.pop());
            countIn--;
            countOut++;
        }
        countOut--;
        return out.pop();
    }
}