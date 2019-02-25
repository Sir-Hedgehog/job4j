package ru.job4j.generic;

import java.util.Iterator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 25.02.2019
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

    boolean set(int index, T model) {
        boolean result = false;
        if (index < position && index > -1) {
            this.models[index] = model;
            result = true;
        }
        return result;
    }

    boolean remove(int index) {
        boolean result = false;
        if (index < position && index > -1) {
            System.arraycopy(this.models, index + 1, this.models, index, this.models.length - index - 1);
            result = true;
        }
        return result;
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
                return current < position;
            }

            @Override
            public T next() {
                return (T) models[current++];
            }
        };
    }
}
