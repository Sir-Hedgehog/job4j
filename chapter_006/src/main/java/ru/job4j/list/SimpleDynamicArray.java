package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 04.03.2019
 */

public class SimpleDynamicArray<E> implements Iterable<E> {
    private int modCount = 0;
    private Object[] values = new Object[3];
    private int position = 0;
    private int size = 0;

    /**
     * Метод реализует добавление элемента в хранилище
     * @param value новый элемент
     */
    public void add(E value) {
        this.values[position++] = value;
        if (position == this.values.length - 1) {
           this.increaseSize();
        }
        size++;
    }

    /**
     * Метод увеличивает размер хранилища
     */
    private void increaseSize() {
        this.values = Arrays.copyOf(this.values, this.values.length + 3);
        modCount++;
    }

    /**
     * Метод выдает элемент по индексу
     * @param index вводится индекс
     * @return существующий элемент
     */
    public E get(int index) {
        return (E) this.values[index];
    }

    /**
     * Метод показывает количество элементов списка
     * @return количество элементов списка
     */
    public int getSize() {
        return this.size;
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
