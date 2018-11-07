package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 *
 * @author Sir-Hedgehog (quaresma_08@mail.ru)
 * @version $Id$
 * @since 16.10.2018
 */

public class PawnBlack implements Figure {
    private final Cell position;

    public PawnBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[Math.abs(dest.x - source.x)];
        if (source.y == dest.y + 1 && source.x == dest.x) {
            steps = new Cell[] {dest};
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}
