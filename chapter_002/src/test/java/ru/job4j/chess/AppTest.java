package ru.job4j.chess;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.BishopBlack;

import static ru.job4j.chess.figures.Cell.*;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    /**
     * Create the test case
     *
     * @param TestName name of the test case
     */
    public AppTest( String TestName )
    {
        super( TestName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigorous Test :-)
     */
    public void whenBishopWillGoThen() {
        Logic logic = new Logic();
        Cell cellOne = new Cell.H3;
        BishopBlack first = new BishopBlack(cellOne);
        logic.add(first);
        Cell cellTwo = new Cell(2, 7);
        BishopBlack second = new BishopBlack(cellTwo);
        logic.add(second);
        logic.move(cellOne, cellTwo);
        assertTrue( true);
    }
}
