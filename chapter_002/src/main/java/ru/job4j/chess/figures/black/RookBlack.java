package ru.job4j.chess.figures.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 *
 * @author Sir-Hedgehog (quaresma_08@mail.ru)
 * @version $Id$
 * @since 21.10.2018
 */

public class RookBlack implements Figure {
    private final Cell position;

    public RookBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] steps = new Cell[Math.abs(dest.x - source.x)];
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        int stepX = deltaX > 0 ? 1 : -1;
        int stepY = deltaY > 0 ? 1 : -1;
        if (Math.abs(deltaX) == Math.abs(deltaY)) {
            throw new ImpossibleMoveException("Данная фигура не может ходить таким образом!");
        }
        for (int index = 0; index != steps.length; index++) {
            if (Math.abs(deltaY) == 0 && Math.abs(deltaX) >= 1) {
                int first = source.x + stepX * (index + 1);
                steps[index] = Cell.values()[first * 8];
            } else {
                int second = source.y + stepY * (index + 1);
                steps[index] = Cell.values()[second];
            }
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }
}
