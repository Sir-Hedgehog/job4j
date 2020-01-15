package ru.job4j.blocking;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 15.01.2020
 */

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    public T queueValue;
    private int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    /**
     * Метод добавляет элементы в очередь.
     * В случае если очередь заполнена, новый элемент добавится только в момент, когда произойдет ее высвобождение (см. метод poll()).
     * @param value - элемент добавления в очередь.
     */

    public synchronized void offer(T value) {
        queueValue = value;
        while (this.size <= queue.size()) {
            try {
                System.out.println("Операция вставки на данный момент невозможна. Ждем свободные ячейки...");
                wait();
                System.out.println("Поступило извещение о том, что есть свободные ячейки...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        T result;
        while (queue.isEmpty()) {
            try {
                System.out.println("В очереди нет элементов. Ждем добавление новых...");
                wait();
                System.out.println("Поступило извещение о том, что очередь не пустая...");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Высвобождаем очередь...");
        result = queue.poll();
        notify();
        return result;
    }

    /**
     * Метод возвращает размер фактической заполненности очереди
     * @return - размер фактической заполненности очереди
     */

    public synchronized int getSize() {
        return queue.size();
    }
}
