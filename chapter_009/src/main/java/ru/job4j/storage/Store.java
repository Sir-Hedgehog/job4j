package ru.job4j.storage;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 24.11.2019
 */

public interface Store {

    boolean accept(Food food);

    void setFood(Food food);
}
