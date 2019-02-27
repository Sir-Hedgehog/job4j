package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 27.02.2019
 */

public class SimpleDynamicArray<E> implements Iterable<E> {
    private int modCount = 0;
    private Object[] values = new Object[3];
    private int position = 0;


    /**
     * Метод реализует добавление элемента в хранилище
     * @param value новый элемент
     */
    void add(E value) {
        this.values[position++] = value;
        if (position == this.values.length - 1) {
           this.increaseSize();
        }
    }

    private void increaseSize() {
        this.values = Arrays.copyOf(this.values, this.values.length + 3);
        modCount++;
    }

    /**
     * Метод выдает элемент по индексу
     * @param index вводится индекс
     * @return существующий элемент
     */
    E get(int index) {
        return (E) this.values[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int current = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return current < position;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) values[current++];
            }
        };
    }
}
