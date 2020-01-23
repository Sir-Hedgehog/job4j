package ru.job4j.tictactoe;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 16.12.2019
 */

public class Computer implements WithoutFeedback {
    private Field field;
    private char mark;
    private String name;
    private static int counter = 0;

    public Computer(Field field, char mark) {
        this.field = field;
        this.mark = mark;
        this.name = "Компьютер" + ++counter;
    }

    /**
     * Метод устанавливает ход компьютера, если он возможен.
     * Если он невозможен, то даем компьютеру возможность сделать другой ход.
     */

    @Override
    public void step() {
        int x = (int) (1 + Math.random() * field.getArray().length);
        int y = (int) (1 + Math.random() * field.getArray().length);
        if (this.field.canMove(x, y, this.getMark())) {
            this.field.paintField();
        } else {
            while (!this.field.canMove(x, y, this.getMark())) {
                x = (int) (1 + Math.random() * field.getArray().length);
                y = (int) (1 + Math.random() * field.getArray().length);
            }
            this.field.paintField();
        }
    }

    /**
     * Метод получает имя игрока
     */

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Метод получает крестик или нолик
     */

    @Override
    public char getMark() {
        return this.mark;
    }
}
