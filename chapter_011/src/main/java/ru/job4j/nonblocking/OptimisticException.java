package ru.job4j.nonblocking;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 30.01.2020
 */

public class OptimisticException extends RuntimeException {
    public OptimisticException() {
        super("Ошибка при обновлении данных в потоке!");
    }
}
