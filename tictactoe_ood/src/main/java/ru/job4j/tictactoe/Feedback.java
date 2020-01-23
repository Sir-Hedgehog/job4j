package ru.job4j.tictactoe;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 16.12.2019
 */

public interface Feedback extends Player {

    void setName();

    void step(int x, int y);

    void setCoordinates();
}
