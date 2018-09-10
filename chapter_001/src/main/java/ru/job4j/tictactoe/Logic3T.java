package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        boolean result = false;
        int horizon;
        int vertical;
        int diagonal1 = 0;
        int diagonal2 = 0;

        for (int out = 0, column = table.length - 1; out < table.length; out++, column--) {
            horizon = 0;
            vertical = 0;
            if (table[out][out].hasMarkX()) {
                diagonal1++;
            }
            if (table[out][column].hasMarkX()) {
                diagonal2++;
            }
            for (int in = 0; in < table.length; in++) {
                if (table[out][in].hasMarkX()) {
                    horizon++;
                }
                if (table[in][out].hasMarkX()) {
                    vertical++;
                }
            }
            if (horizon == 3 || vertical == 3 || diagonal1 == 3 || diagonal2 == 3) {
                result = true;
                break;
            }
        }
        return result;
    }


    public boolean isWinnerO() {
        boolean result = false;
        int horizon;
        int vertical;
        int diagonal1 = 0;
        int diagonal2 = 0;

        for (int out = 0, column = table.length - 1; out < table.length; out++, column--) {
            horizon = 0;
            vertical = 0;
            if (table[out][out].hasMarkO()) {
                diagonal1++;
            }
            if (table[out][column].hasMarkO()) {
                diagonal2++;
            }
            for (int in = 0; in < table.length; in++) {
                if (table[out][in].hasMarkO()) {
                    horizon++;
                }
                if (table[in][out].hasMarkO()) {
                    vertical++;
                }
            }
            if (horizon == 3 || vertical == 3 || diagonal1 == 3 || diagonal2 == 3) {
                result = true;
                break;
            }
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
            if (!result) {
                break;
            }
        }
        return result;
    }
}
