package ru.job4j.blocking;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 7.0
 * @since 03.03.2020
 */

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    /**
     * Метод добавляет элементы в очередь.
     * В случае если очередь заполнена, новый элемент добавится только в момент, когда произойдет ее высвобождение (см. метод poll()).
     * @param value - элемент добавления в очередь.
     */

    public synchronized void offer(T value) {
        while (this.size <= queue.size()) {
            try {
                System.out.println("Операция вставки на данный момент невозможна. Ждем свободные ячейки...");
                wait();
                System.out.println("Поступило извещение о том, что есть свободные ячейки...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Заполняем очередь...");
        queue.offer(value);
        notify();
    }

    /**
     * Метод удаляет элементы из очереди.
     * В случае если очередь пустая, метод вдальнейшем удалит первый попавший в очередь элемент (см. метод offer(T value)).
     * @return - удаленный элемент.
     */

    public synchronized T poll() {
        T result = null;
        while (queue.isEmpty() && !Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("В очереди нет элементов. Ждем добавление новых...");
                wait();
                System.out.println("Поступило извещение о том, что очередь не пустая...");
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        if (!Thread.currentThread().isInterrupted()) {
            System.out.println("Высвобождаем очередь...");
            result = queue.poll();
            notify();
        }
        return result;
    }

    /**
     * Метод возвращает размер фактической заполненности очереди
     * @return - размер фактической заполненности очереди
     */

    public synchronized int getFactSize() {
        return queue.size();
    }
    /**
     * Метод возвращает максимально возможный размер очереди
     * @return - максимально возможный размер очереди
     */

    public synchronized int getNominalSize() {
        return this.size;
    }
}
