package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public void push(T value) {
        while (!out.isEmpty()) {
            in.push(out.pop());
        }
        in.push(value);
    }

    public T poll() {
        if (in.isEmpty() && out.isEmpty()) {
            throw new NoSuchElementException();
        }
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
        return out.pop();
    }
}