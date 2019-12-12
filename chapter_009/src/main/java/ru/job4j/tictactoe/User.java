package ru.job4j.tictactoe;

import java.util.Scanner;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 12.12.2019
 */

public class User implements Step {
    private Field field;

    public User(Field field) {
        this.field = field;
    }

    /**
     * Метод устанавливает ход игрока, если он возможен.
     * Если он невозможен, то даем игроку возможность сделать другой ход.
     * @param x - координата x
     * @param y - координата y
     */

    @Override
    public void step(int x, int y) {
        if (this.field.canMove(x, y, 'x')) {
            this.field.paintField();
        } else {
            while (!this.field.canMove(x, y, 'x')) {
                System.out.println("Это поле уже занято! Введите другие координаты:");
                Scanner coordinate = new Scanner(System.in);
                System.out.print("x = ");
                x = coordinate.nextInt();
                System.out.print("y = ");
                y = coordinate.nextInt();
            }
            this.field.paintField();
        }
    }
}
