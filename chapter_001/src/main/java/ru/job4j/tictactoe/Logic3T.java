package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        boolean resultHorizontal = true;
        for (int out = 0; out < table.length; out++) {
            for (int in = 1; in < table.length; in++) {
                if (table[out][0].hasMarkX() != table[out][in].hasMarkX()) {
                    resultHorizontal = false;
                    break;
                }
            }
        }

        boolean resultVertical = true;
        for (int out = 0; out < table.length; out++) {
            for (int in = 1; in < table.length; in++) {
                if (table[0][out].hasMarkX() != table[in][out].hasMarkX()) {
                    resultVertical = false;
                    break;
                }
            }
        }

        boolean resultDiagonalOne = true;
        for (int line = 1; line < table.length; line++) {
            if (table[0][0].hasMarkX() != table[line][line].hasMarkX()) {
                resultDiagonalOne = false;
            }
        }

        boolean resultDiagonalSecond = true;
        for (int line = 1, column = table.length - 2; line < table.length; line++, column--) {
            if (table[0][table.length - 1].hasMarkX() != table[line][column].hasMarkX()) {
                resultDiagonalSecond = false;
            }
        }

        return resultHorizontal || resultVertical || resultDiagonalOne || resultDiagonalSecond;
    }

    public boolean isWinnerO() {
        boolean resultHorizontal = true;
        for (int out = 0; out < table.length; out++) {
            for (int in = 1; in < table.length; in++) {
                if (table[out][0].hasMarkO() != table[out][in].hasMarkO()) {
                    resultHorizontal = false;
                    break;
                }
            }
        }

        boolean resultVertical = true;
        for (int out = 0; out < table.length; out++) {
            for (int in = 1; in < table.length; in++) {
                if (table[0][out].hasMarkO() != table[in][out].hasMarkO()) {
                    resultVertical = false;
                    break;
                }
            }
        }

        boolean resultDiagonalOne = true;
        for (int line = 1; line < table.length; line++) {
            if (table[0][0].hasMarkO() != table[line][line].hasMarkO()) {
                resultDiagonalOne = false;
            }
        }

        boolean resultDiagonalSecond = true;
        for (int line = 1, column = table.length - 2; line < table.length; line++, column--) {
            if (table[0][table.length - 1].hasMarkO() != table[line][column].hasMarkO()) {
                resultDiagonalSecond = false;
            }
        }

        return resultHorizontal || resultVertical || resultDiagonalOne || resultDiagonalSecond;
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
            if (result == true) {
                break;
            }
        }
        return result;
    }
}
