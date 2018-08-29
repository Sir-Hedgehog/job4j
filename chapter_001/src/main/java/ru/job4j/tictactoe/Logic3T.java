package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        boolean result = false;
        if ((table[0][0].hasMarkX() && table[0][1].hasMarkX() && table[0][2].hasMarkX())
                || (table[1][0].hasMarkX() && table[1][1].hasMarkX() && table[1][2].hasMarkX())
                || (table[2][0].hasMarkX() && table[2][1].hasMarkX() && table[2][2].hasMarkX())
                || (table[0][0].hasMarkX() && table[1][0].hasMarkX() && table[2][0].hasMarkX())
                || (table[0][1].hasMarkX() && table[1][1].hasMarkX() && table[2][1].hasMarkX())
                || (table[0][2].hasMarkX() && table[1][2].hasMarkX() && table[2][2].hasMarkX())
                || (table[0][0].hasMarkX() && table[1][1].hasMarkX() && table[2][2].hasMarkX())
                || (table[0][2].hasMarkX() && table[1][1].hasMarkX() && table[2][0].hasMarkX())) {
            result = true;
        }
        return result;
    }

    public boolean isWinnerO() {
        boolean result = false;
        if ((table[0][0].hasMarkO() && table[0][1].hasMarkO() && table[0][2].hasMarkO())
                || (table[1][0].hasMarkO() && table[1][1].hasMarkO() && table[1][2].hasMarkO())
                || (table[2][0].hasMarkO() && table[2][1].hasMarkO() && table[2][2].hasMarkO())
                || (table[0][0].hasMarkO() && table[1][0].hasMarkO() && table[2][0].hasMarkO())
                || (table[0][1].hasMarkO() && table[1][1].hasMarkO() && table[2][1].hasMarkO())
                || (table[0][2].hasMarkO() && table[1][2].hasMarkO() && table[2][2].hasMarkO())
                || (table[0][0].hasMarkO() && table[1][1].hasMarkO() && table[2][2].hasMarkO())
                || (table[0][2].hasMarkO() && table[1][1].hasMarkO() && table[2][0].hasMarkO())) {
            result = true;
        }
        return result;
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
            }
        return result;
    }
}
