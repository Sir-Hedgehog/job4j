package ru.job4j.counter;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 11.01.2020
 */

@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    /**
     * Метод инкрементирует значение счетчика
     */

    public synchronized void increment() {
        this.value++;
    }

    /**
     * Метод показывает текущее значение счетчика
     * @return - значение счетчика
     */

    public synchronized int get() {
        return this.value;
    }
}
