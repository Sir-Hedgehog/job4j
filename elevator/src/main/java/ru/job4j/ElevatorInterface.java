package ru.job4j;

import java.util.concurrent.ExecutionException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 19.03.2020
 */

public interface ElevatorInterface {
    void comeIn() throws ExecutionException, InterruptedException;
    void goOut() throws ExecutionException, InterruptedException;
    void move() throws ExecutionException, InterruptedException;
    void closeDoors();
    boolean closePool();
}
