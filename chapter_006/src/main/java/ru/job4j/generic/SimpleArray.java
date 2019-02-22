package ru.job4j.generic;

import java.util.Iterator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 22.02.2019
 */

public class SimpleArray<T> implements Iterable<T> {
    private Object[] models;
    private int position = 0;

    SimpleArray(int size) {
        this.models = new Object[size];
    }

    void add(T model) {
        this.models[position++] = model;
    }

    void set(int index, T model) {
        if (index < this.models.length) {
            this.models[index] = model;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    void remove(int index) {
        System.arraycopy(this.models, index + 1, this.models, index, this.models.length - index - 1);
    }

    T get(int index) {
        return (T) this.models[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < models.length && models[current] != null;
            }

            @Override
            public T next() {
                return (T) models[current++];
            }
        };
    }
}
