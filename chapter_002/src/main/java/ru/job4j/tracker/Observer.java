package ru.job4j.tracker;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 13.09.2020
 */

public interface Observer<Item> {

    /**
     * Метод получает текущую заявку
     * @param item - текущая заявка
     */

    void receive(Item item);
}
