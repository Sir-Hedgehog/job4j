package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 01.03.2019
 */

public class SimpleQueue<V> implements Iterable<V> {
    private int modCount = 0;
    private int size = 0;
    private Node<V> first;
    private Node<V> last;

    public void push(V date) {
        Node<V> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        modCount++;
        size++;
    }

    public V poll() {
        Node<V> result = this.first;
        if (size > 0) {
            if (size != 1) {
                while (result.next.next != null) {
                    result = result.next;
                }
                this.last = result.next;
                result.next = null;
            } else {
                this.last = this.first;
                this.first = null;
            }
        }
        size--;
        modCount++;
        return this.last.date;
    }

    private static class Node<V> {
        V date;
        Node<V> next;

        Node(V date) {
            this.date = date;
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            Node<V> actual = first;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public V next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                V date = actual.date;
                actual = actual.next;
                return date;
            }
        };
    }
}
