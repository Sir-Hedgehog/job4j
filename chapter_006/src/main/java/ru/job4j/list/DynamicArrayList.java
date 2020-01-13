package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 13.01.2019
 */

public class DynamicArrayList<E> implements Iterable<E> {
    //private int modCount = 0;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        //modCount++;
    }

    /**
     * Метод получения элемента по индексу
     * @param index - индекс элемента в списке
     * @return существующий элемент
     */

    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    private static class Node<E> {
        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            //private int expectedModCount = modCount;
            Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                /*if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }*/
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                E date = current.date;
                current = current.next;
                return date;
            }
        };
    }
}
