package ru.job4j.bomberman;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 16.02.2020
 */

public class StartGame {
    public static void main(String[] args) {
        final Board board = new Board(5, 5);
        board.start();
    }
}
