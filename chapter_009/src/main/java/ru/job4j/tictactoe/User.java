package ru.job4j.tictactoe;

import java.util.Scanner;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 16.12.2019
 */

public class User implements Feedback {
    private Field field;
    private char mark;
    private String name;
    private static int counter = 0;
    private Scanner coordinate = new Scanner(System.in);
    private int x;
    private int y;

    public User(Field field, char mark) {
        this.field = field;
        this.mark = mark;
        ++counter;
        this.name = this.getName();
    }

    /**
     * Метод дает возможность игроку сделать свой ход
     */

    @Override
    public void setCoordinates() {
        System.out.println("Ваш ход, " + this.getName() + ":");
        System.out.print("x = ");
        this.x = coordinate.nextInt();
        System.out.print("y = ");
        this.y = coordinate.nextInt();
    }

    /**
     * Метод получает координату x
     */

    public int getX() {
        return this.x;
    }

    /**
     * Метод получает координату y
     */

    public int getY() {
        return this.y;
    }

    /**
     * Метод проверяет ход игрока на валидность.
     * Если он (ход) невозможен, то даем игроку возможность сделать другой ход.
     * @param x - координата x
     * @param y - координата y
     */

    @Override
    public void step(int x, int y) {
        if (this.field.canMove(x, y, this.getMark())) {
            this.field.paintField();
        } else {
            while (!this.field.canMove(x, y, this.getMark())) {
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

    /**
     * Метод устанавливает имя игрока
     */

    @Override
    public void setName() {
        if (counter == 1) {
            System.out.print("Введите имя первого игрока: ");
        } else if (counter == 2) {
            System.out.print("Введите имя второго игрока: ");
        }
        Scanner name = new Scanner(System.in);
        this.name = name.next();
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
