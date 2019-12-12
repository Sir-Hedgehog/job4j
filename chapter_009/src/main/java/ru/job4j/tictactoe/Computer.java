package ru.job4j.tictactoe;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 12.12.2019
 */

public class Computer implements Step {
    private Field field;

    public Computer(Field field) {
        this.field = field;
    }

    /**
     * Метод устанавливает ход компьютера, если он возможен.
     * Если он невозможен, то даем компьютеру возможность сделать другой ход.
     * @param x - координата x
     * @param y - координата y
     */

    @Override
    public void step(int x, int y) {
        if (this.field.canMove(x, y, 'o')) {
            this.field.paintField();
        } else {
            while (!this.field.canMove(x, y, 'o')) {
                x = (int) (1 + Math.random() * field.getArray().length);
                y = (int) (1 + Math.random() * field.getArray().length);
            }
            this.field.paintField();
        }
    }
}
