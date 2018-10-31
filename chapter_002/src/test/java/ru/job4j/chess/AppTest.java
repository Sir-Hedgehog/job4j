package ru.job4j.chess;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.BishopBlack;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 31.10.2018
 */

public class AppTest {

    @Test
    public void whenBishopWillGoThen() {
        Logic logic = new Logic();
        Cell one = Cell.A3;
        BishopBlack first = new BishopBlack(one);
        logic.add(first);
        Cell two = Cell.C1;
        BishopBlack second = new BishopBlack(two);
        logic.add(second);
        assertTrue(logic.move(one, two));
    }
}
