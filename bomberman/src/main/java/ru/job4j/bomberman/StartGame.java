package ru.job4j.bomberman;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 26.01.2020
 */

public class StartGame {
    public static void main(String[] args) throws InterruptedException {
        final Board board = new Board(4, 4,5, 5);
        Thread first = new Thread(board);
        first.start();
        first.join();
    }
}
