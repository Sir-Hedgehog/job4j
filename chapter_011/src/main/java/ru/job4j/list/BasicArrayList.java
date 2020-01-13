package ru.job4j.list;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 13.01.2020
 */

public interface BasicArrayList<E> extends Iterable<E> {
    void add(E date);
    E get(int index);
}
