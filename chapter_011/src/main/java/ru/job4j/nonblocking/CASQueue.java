package ru.job4j.nonblocking;

import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 10.05.2020
 */

@ThreadSafe
public class CASQueue<T> implements CASQueueInterface<T> {
    private final Node<T> general = new Node<>(null, null);
    private final AtomicReference<Node<T>> tail = new AtomicReference<>(general);
    private final AtomicReference<Node<T>> head = new AtomicReference<>(general);

    /**
     * Метод добавляет уникальный элемент в потокобезопасную очередь
     * @param value - элемент
     */

    @Override
    public void push(T value) {
        Node<T> newElement = new Node<>(value, null);
        Node<T> current;
        do {
            current = tail.get();
            tail.compareAndSet(current, newElement);
        } while (!current.next.compareAndSet(null, newElement));
    }

    /**
     * Метод выдает в качестве результата удаленный с потокобезопасной очереди элемент
     * @return T - элемент
     */

    @Override
    public T poll() {
        Node<T> reference;
        Node<T> temp;
        do {
            reference = head.get();
            if (reference == null) {
                throw new IllegalStateException("Queue is empty!");
            }
            temp = reference.next.get();
        } while (!head.compareAndSet(reference, temp));
        return reference.next.get().value;
    }

    /**
     * Класс позволяет сформировать группу элементов и связей между ними (в нашем случае - потокобезопасную очередь)
     */

    private static final class Node<T> {
        final T value;
        final AtomicReference<Node<T>> next;

        public Node(final T value, Node<T> next) {
            this.value = value;
            this.next = new AtomicReference<>(next);
        }
    }
}
