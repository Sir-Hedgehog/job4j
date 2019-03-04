package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 04.02.2019
 */

public class SimpleStack<T> implements Iterable<T> {
    private int modCount = 0;
    private int size = 0;
    private Node<T> first;

    public void push(T date) {
        Node<T> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.modCount++;
        this.size++;
    }

    public T poll() {
        Node<T> result = this.first;
        this.first = this.first.next;
        this.size--;
        return result.date;
    }

    public boolean isEmpty() {
        return this.getSize() == 0;
    }

    public int getSize() {
        return this.size;
    }

    private static class Node<T> {
        T date;
        Node<T> next;

        Node(T date) {
            this.date = date;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            Node<T> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                T date = current.date;
                current = current.next;
                return date;
            }
        };
    }
}
