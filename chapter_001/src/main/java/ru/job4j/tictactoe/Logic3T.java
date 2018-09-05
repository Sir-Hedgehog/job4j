package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        boolean resultHorizontal = true;
        boolean resultVertical = true;
        boolean resultFirstDiagonal = true;
        boolean resultSecondDiagonal = true;

        for (int out = 0; out < table.length; out++) {
            for (int in = 0; in < table.length; in++) {
                if (!table[out][in].hasMarkX()) {
                    resultHorizontal = false;
                    break;
                }
            }
        }

        if (!resultHorizontal) {
            for (int out = 0; out < table.length; out++) {
                for (int in = 0; in < table.length; in++) {
                    if (!table[in][out].hasMarkX()) {
                        resultVertical = false;
                        break;
                    }
                }
            }
        }

        if (!resultVertical) {
            for (int line = 0; line < table.length; line++) {
                if (!table[line][line].hasMarkX()) {
                    resultFirstDiagonal = false;
                }
            }
        }


        if (!resultFirstDiagonal) {
            for (int line = 0, column = table.length - 1; line < table.length; line++, column--) {
                if (!table[line][column].hasMarkX()) {
                    resultSecondDiagonal = false;
                }
            }
        }

        return resultHorizontal || resultVertical || resultFirstDiagonal || resultSecondDiagonal;
    }

    public boolean isWinnerO() {
        boolean resultHorizontal = true;
        boolean resultVertical = true;
        boolean resultFirstDiagonal = true;
        boolean resultSecondDiagonal = true;

        for (int out = 0; out < table.length; out++) {
            for (int in = 0; in < table.length; in++) {
                if (!table[out][in].hasMarkO()) {
                    resultHorizontal = false;
                    break;
                }
            }
        }

        if (!resultHorizontal) {
            for (int out = 0; out < table.length; out++) {
                for (int in = 1; in < table.length; in++) {
                    if (table[in][out].hasMarkO() != table[in][out].hasMarkO()) {
                        resultVertical = false;
                        break;
                    }
                }
            }
        }

        if (!resultVertical) {
            for (int line = 0; line < table.length; line++) {
                if (!table[line][line].hasMarkO()) {
                    resultFirstDiagonal = false;
                }
            }
        }

        if (!resultFirstDiagonal) {
            for (int line = 0, column = table.length - 1; line < table.length; line++, column--) {
                if (!table[line][column].hasMarkO()) {
                    resultSecondDiagonal = false;
                }
            }
        }

        return resultHorizontal || resultVertical || resultFirstDiagonal || resultSecondDiagonal;
    }

    public boolean hasGap() {
        boolean result = false;
        for (int out = 0; out < table.length; out++) {
            for (int in = 0; in < table.length; in++) {
                if (!table[out][in].hasMarkX() && !table[out][in].hasMarkO()) {
                    result = true;
                    break;
                }
            }
            if (!result) {
                break;
            }
        }
        return result;
    }
}
