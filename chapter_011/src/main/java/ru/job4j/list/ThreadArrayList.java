package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.Iterator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 13.01.2020
 */

@ThreadSafe
public class ThreadArrayList<E> implements BasicArrayList<E> {
    @GuardedBy("this")
    private DynamicArrayList<E> array;

    public ThreadArrayList(DynamicArrayList<E> array) {
        this.array = array;
    }

    /**
     * Метод вставляет в начало списка данные
     * @param date - элемент вставки
     */

    @Override
    public synchronized void add(E date) {
        array.add(date);
    }

    /**
     * Метод получения элемента по индексу
     * @param index - индекс элемента в списке
     * @return - существующий элемент
     */

    @Override
    public synchronized E get(int index) {
        return array.get(index);
    }

    /**
     * Метод позволяет проходить по списку элементов
     * @return - результат итерации
     */

    @Override
    public synchronized Iterator<E> iterator() {
        return copy(this.array).iterator();
    }

    /**
     * Метод делает копию данных списка для работы итератора с многопоточностью
     * @param input - оригинальный список
     * @return - копия списка
     */

    private synchronized Iterable<E> copy(DynamicArrayList<E> input) {
        Iterable<E> result = new DynamicArrayList<>();
        for (E component : input) {
            array.add(component);
        }
        return result;
    }
}
