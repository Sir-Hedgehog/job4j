package ru.job4j.tictactoe;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 16.12.2019
 */

public class Field {
    private int range;
    private int y;
    private char[][] arrayOfSteps;
    private char mark;

    public Field(int range) {
        this.range = range;
        arrayOfSteps = new char[this.range][this.range];
    }

    /**
     * Метод передает массив, отражающий состояние поля игры
     * @return - массив, отражающий состояние поля игры
     */

    public char[][] getArray() {
        return arrayOfSteps;
    }

    /**
     * Метод передает последний ход (крестик или нолик)
     * @return - крестик или нолик
     */

    public char getMark() {
        return this.mark;
    }

    /**
     * Метод рисует поле игры
     */

    public void paintField() {
        System.out.print("x\\y");
        for (int index = 1; index <= range; index++) {
            System.out.print("   " + index + ": ");
        }
        System.out.println();
        for (int out = 1; out <= range; out++) {
            System.out.print("   ");
            for (int in = 1; in <= range; in++) {
                if (in == range) {
                    System.out.println(" -----");
                    paintCells(out);
                } else {
                    System.out.print(" -----");
                }
            }
        }
        System.out.print("   ");
        for (int index = 1; index <= range; index++) {
            System.out.print(" -----");
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Метод вносит правки по изменению состояния поля
     * @param out - вертикальные (неизменяемые) стенки
     */

    private void paintCells(int out) {
        for (int index = 1; index <= range; index++) {
            if (index == 1) {
                System.out.print(out + ": ");
            }
        }
        for (int inOfArray = 0; inOfArray < arrayOfSteps.length; inOfArray++) {
            if (arrayOfSteps[out - 1][inOfArray] == '\0') {
                System.out.print("|  " + ' ' + "  ");
            } else {
                System.out.print("|  " + arrayOfSteps[out - 1][inOfArray] + "  ");
            }
        }
        System.out.println("|");
    }

    /**
     * Метод проверяет, можно ли ходить на выбранную клетку поля
     * @param x - координата x
     * @param y - координата y
     * @param mark - крестик или нолик
     * @return - можно или нельзя
     */

    public boolean canMove(int x, int y, char mark) {
        boolean result = false;
        this.mark = mark;
        for (int out = 0; out < arrayOfSteps.length; out++) {
            for (int in = 0; in < arrayOfSteps.length; in++) {
                if (out + 1 == x && in + 1 == y && arrayOfSteps[out][in] == '\0') {
                    arrayOfSteps[out][in] = mark;
                    result = true;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }
}
