package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements List<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modCount;

    private class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, value, null);
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        last = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        Iterator<E> iter = this.iterator();
        while (iter.hasNext() && count <= index) {
            E temp = iter.next();
            if (count == index) {
                return temp;
            }
            count++;
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor;
            Node<E> nextEl = first;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                Node<E> temp = nextEl;
                nextEl = temp.next;
                cursor++;
                return temp.item;
            }
        };
    }
}