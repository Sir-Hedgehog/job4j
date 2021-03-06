package ru.job4j.blocking;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.0
 * @since 17.01.2020
 */

public class Producer<T> {
    private final SimpleBlockingQueue<T> queue;

    public Producer(SimpleBlockingQueue<T> queue) {
        this.queue = queue;
    }

    public void offer(T value) {
        queue.offer(value);
    }
}
