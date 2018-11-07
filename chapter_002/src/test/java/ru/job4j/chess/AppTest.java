package ru.job4j.chess;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.BishopBlack;
import ru.job4j.chess.figures.black.PawnBlack;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 31.10.2018
 */

public class AppTest {
    @Test
    public void whenBishopBlackWillGoThen() throws ImpossibleMoveException, FigureNotFoundException, OccupiedWayException {
        Logic logic = new Logic();
        BishopBlack bishop = new BishopBlack(Cell.H3);
        logic.add(bishop);
        //bishop.way(Cell.H3, Cell.C8);
        assertThat(logic.move(Cell.H3, Cell.C8), is(true));
    }

    @Test
    public void whenPawnBlackWillGoThen() throws ImpossibleMoveException, FigureNotFoundException, OccupiedWayException {
        Logic logic = new Logic();
        PawnBlack pawn = new PawnBlack(Cell.B1);
        logic.add(pawn);
        assertThat(logic.move(Cell.B1, Cell.C1), is(true));
    }
}
