package ru.job4j.list;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 04.03.2019
 */

public class SimpleQueue<T> {
    private SimpleStack<T> one;
    private SimpleStack<T> two;

    public SimpleQueue(SimpleStack<T> one, SimpleStack<T> two) {
        this.one = one;
        this.two = two;
    }

    public void push(T date) {
        this.one.push(date);
    }

    public T poll() {
        if (this.two.isEmpty()) {
            while (!this.one.isEmpty()) {
                this.two.push(this.one.poll());
            }
        }
        return this.two.poll();
    }
}
