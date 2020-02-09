package ru.job4j;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 09.02.2020
 */

public class House {
    private final int max;

    public House(int max) {
        this.max = max;
    }

    /**
     * Метод возвращает количество этажей в доме
     * @return - количество этажей
     */

    public int getMax() {
        return max;
    }
}
