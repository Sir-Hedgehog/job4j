package ru.job4j.blocking;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 16.01.2020
 */

public class Consumer<T> {
    private final SimpleBlockingQueue<T> queue;

    public Consumer(SimpleBlockingQueue<T> queue) {
        this.queue = queue;
    }

    public T poll() {
        return queue.poll();
    }
}
