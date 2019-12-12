package ru.job4j.tictactoe;

import java.util.Scanner;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 12.12.2019
 */

public class TictactoeTest {
    public static void main(String[] args) {
        Field field = new Field(3);
        field.paintField();
        Computer computer = new Computer(field);
        User user = new User(field);
        Checker counter = new Checker();
        while (!counter.checkTheEnd(field)) {
            computer.step((int) (1 + Math.random() * field.getArray().length), (int) (1 + Math.random() * field.getArray().length));
            if (counter.checkTheEnd(field)) {
                break;
            }
            Scanner coordinate = new Scanner(System.in);
            System.out.println("Ваш ход, сэр:");
            System.out.print("x = ");
            int x = coordinate.nextInt();
            System.out.print("y = ");
            int y = coordinate.nextInt();
            user.step(x, y);
        }
    }
}
