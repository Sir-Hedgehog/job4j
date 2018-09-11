package ru.job4j.tictactoe;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Logic3TTest {

    @Test
    public void whenHasXWinnerInHorizontal() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(true), new Figure3T(true)},
                {new Figure3T(), new Figure3T(false), new Figure3T()},
                {new Figure3T(false), new Figure3T(true), new Figure3T(false)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXWinnerInVertical() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(false), new Figure3T(true)},
                {new Figure3T(), new Figure3T(false), new Figure3T(true)},
                {new Figure3T(false), new Figure3T(), new Figure3T(true)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXWinnerInFirstDiagonal() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(false), new Figure3T(true)},
                {new Figure3T(), new Figure3T(true), new Figure3T()},
                {new Figure3T(false), new Figure3T(false), new Figure3T(true)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXWinnerInSecondDiagonal() {
        Figure3T[][] table = {
                {new Figure3T(false), new Figure3T(false), new Figure3T(true)},
                {new Figure3T(), new Figure3T(true), new Figure3T()},
                {new Figure3T(true), new Figure3T(false), new Figure3T(false)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    @Test
    public void whenHasOWinnerInHorizontal() {
        Figure3T[][] table = {
                {new Figure3T(false), new Figure3T(true), new Figure3T(true)},
                {new Figure3T(false), new Figure3T(false), new Figure3T(false)},
                {new Figure3T(true), new Figure3T(false), new Figure3T(true)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    @Test
    public void whenHasOWinnerInVertical() {
        Figure3T[][] table = {
                {new Figure3T(false), new Figure3T(false), new Figure3T(true)},
                {new Figure3T(false), new Figure3T(true), new Figure3T()},
                {new Figure3T(false), new Figure3T(true), new Figure3T(false)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    @Test
    public void whenHasOWinnerInFirstDiagonal() {
        Figure3T[][] table = {
                {new Figure3T(false), new Figure3T(false), new Figure3T(true)},
                {new Figure3T(true), new Figure3T(false), new Figure3T()},
                {new Figure3T(false), new Figure3T(true), new Figure3T(false)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    @Test
    public void whenHasOWinnerInSecondDiagonal() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(false), new Figure3T(false)},
                {new Figure3T(true), new Figure3T(false), new Figure3T()},
                {new Figure3T(false), new Figure3T(true), new Figure3T(false)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    @Test
    public void whenHasGasOfWinnerX() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(true), new Figure3T(true)},
                {new Figure3T(), new Figure3T(false), new Figure3T()},
                {new Figure3T(false), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.hasGap(), is(true));
    }

    @Test
    public void whenHasGasOfWinnerO() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(), new Figure3T(false)},
                {new Figure3T(), new Figure3T(false), new Figure3T()},
                {new Figure3T(false), new Figure3T(), new Figure3T(true)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.hasGap(), is(true));
    }

    @Test
    public void whenHasGasOfGameOver() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(true), new Figure3T(false)},
                {new Figure3T(false), new Figure3T(false), new Figure3T(true)},
                {new Figure3T(true), new Figure3T(false), new Figure3T(true)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.hasGap(), is(false));
    }
}