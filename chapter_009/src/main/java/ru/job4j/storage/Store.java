package ru.job4j.storage;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.0
 * @since 27.11.2019
 */

public interface Store {
    boolean accept(Food food);
}
