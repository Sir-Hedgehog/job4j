package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 06.03.2019
 */

public class SimpleMap<K, V> implements Iterable {
    private int modCount = 0;
    private Node<K, V>[] table = new Node[10];
    private int size = 0;

    /**
     * Метод реализует добавление элемента в хранилище
     * @param key новый ключ
     * @param value новое значение
     */
    boolean insert(K key, V value) {
        boolean result;
        Node<K, V> node = new Node<>(key, value);
        int index = this.hash(key);
        if (this.table[index] != null) {
            result = false;
        } else {
            this.table[index] = node;
            result = true;
        }
        if (this.size == this.table.length) {
            this.increaseSize();
        }
        size++;
        modCount++;
        return result;
    }

    /**
     * Метод определяет ячейку для нового элемента
     * @return возвращает номер ячейки
     */
    private int hash(K key) {
        return key.hashCode() % table.length;
    }

    /**
     * Метод увеличивает размер хранилища
     */
    private void increaseSize() {
        this.table = Arrays.copyOf(this.table, this.table.length * 2);
    }

    /**
     * Метод выдает значение по ключу
     * @param key вводится ключ
     * @return значение по ключу
     */
    V get(K key) {
        V result = null;
        int index = this.hash(key);
        if (this.table[index] != null) {
            Node<K, V> current = this.table[index];
            result = current.value;
        }
        return result;
    }


    /**
     * Метод удаляет элемент по ключу
     */
    boolean delete(K key) {
        boolean result = false;
        int index = this.hash(key);
        if (this.table[index] != null) {
            this.table[index] = null;
            result = true;
        }
        size--;
        modCount++;
        return result;
    }

    public static class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator<>() {
            private int current = -1;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return current < table.length;
            }

            @Override
            public K next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                current++;
                while (table[current] == null) {
                    current++;
                }
                return table[current].key;
            }
        };
    }

}
