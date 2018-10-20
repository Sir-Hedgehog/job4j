package ru.job4j.chess;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import java.util.Optional;

/**
 *
 * @author Sir-Hedgehog (quaresma_08@mail.ru)
 * @version $Id$
 * @since 20.10.2018
 */

public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, FigureNotFoundException, OccupiedWayException {
        boolean rst = false;
        int index = this.findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException("Фигура не найдена!");
        }
        Cell[] steps = this.figures[index].way(source, dest);
        for (Cell out : steps) {
            for (Figure in : figures) {
                if (out.equals(in.position()) && in.position() != null) {
                    throw new OccupiedWayException("Этот ход закрыт другой фигурой!");
                }
            }
        }
        if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
            this.figures[index] = this.figures[index].copy(dest);
            rst = true;
        }

        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}