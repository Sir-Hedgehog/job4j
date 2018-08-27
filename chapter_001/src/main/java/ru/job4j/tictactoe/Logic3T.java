package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        boolean result = true;
        for (int index1 = 0, index = table.length - 1; index1 < table.length - 1; index1++, index--) {
            if (table[0][0] == table[0][index] | table[1][0] == table[1][index] | table[2][0] == table[2][index] || table[0][table.length - 1] == table[index][index1] || table[0][0] == table[index][index] || table[0][0] == table[index][0] || table[0][1] == table[index][1] || table[0][2] == table[index][2]) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinnerO() {
        boolean result = true;
        for (int index1 = 0, index = table.length - 1; index1 < table.length - 1; index1++, index--) {
            if (table[0][0] == table[0][index] | table[1][0] == table[1][index] | table[2][0] == table[2][index] | table[0][table.length - 1] == table[index][index1] | table[0][0] == table[index][index] | table[0][0] == table[index][0] | table[0][1] == table[index][1] | table[0][2] == table[index][2]) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean hasGap() {
        boolean result = false; // если элемента нет в массиве, то возвращаем -1.
        for (int index1 = 0, index = table.length - 1; index1 < table.length - 1; index1++, index--) {
            if ((table[0][0] != table[0][index] && table[1][0] != table[1][index] && table[2][0] != table[2][index] && table[0][table.length - 1] != table[index][index1] && table[0][0] != table[index][index] && table[0][0] != table[index][0] && table[0][1] != table[index][1] && table[0][2] != table[index][2]) || (table[0][0] == table[0][index] || table[1][0] == table[1][index] || table[2][0] == table[2][index] || table[0][table.length - 1] == table[index][index1] || table[0][0] == table[index][index] || table[0][0] == table[index][0] || table[0][1] == table[index][1] || table[0][2] == table[index][2])) {
                result = true;
                break;
            }
        }
        return result;
    }
}
