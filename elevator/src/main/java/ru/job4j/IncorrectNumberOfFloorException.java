package ru.job4j;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 09.02.2020
 */

public class IncorrectNumberOfFloorException extends Exception {
    public IncorrectNumberOfFloorException() {
        super("Такого этажа не существует!");
    }
}
