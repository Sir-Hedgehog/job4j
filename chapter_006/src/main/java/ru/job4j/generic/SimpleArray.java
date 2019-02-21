package ru.job4j.generic;

import java.util.Iterator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 21.02.2019
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
        for (Object current : this.models) {
            if (current != null && this.models[index] == current) {
                this.models[index] = model;
            }
        }
    }

    void remove(int index) {
        for (Object current : this.models) {
            if (current != null && this.models[index] == current) {
                System.arraycopy(this.models, index + 1, this.models, index, this.models.length - index - 1);
                break;
            }
        }
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
