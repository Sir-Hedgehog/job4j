package ru.job4j.nonblocking;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 10.05.2020
 */

public interface CASQueueInterface<T> {
    void push(T value);
    T poll();
}
