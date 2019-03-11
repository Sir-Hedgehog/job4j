package ru.job4j.tree;

import java.util.Optional;

public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Метод добавляет элемент child в parent.
     * @param parent - родитель.
     * @param child - ребенок.
     */
    boolean add(E parent, E child);

    /**
     * Метод ищет элемент в дереве.
     * @param value значение.
     * @return искомый "лист" дерева
     */
    Optional<Node<E>> findBy(E value);
}