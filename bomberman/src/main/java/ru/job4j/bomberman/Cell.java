package ru.job4j.bomberman;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 16.02.2020
 */

public class Cell {
    private int x;
    private int y;
    public ReentrantLock locker;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        locker = new ReentrantLock();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
