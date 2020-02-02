package ru.job4j.bomberman;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 02.02.2020
 */

public class StartGame {
    public static void main(String[] args) throws InterruptedException {
        final Board board = new Board(4, 4, 5, 5);
        Thread first = new Thread(board);
        first.start();
        first.join();
    }
}
