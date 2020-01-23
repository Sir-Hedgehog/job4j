package ru.job4j.tictactoe;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 16.12.2019
 */

public class TictactoeTest1 {

    /**
     * Игра компьютера с компьютером
     */

    public static void main(String[] args) {
        Field field = new Field(3);
        field.paintField();
        Computer computer1 = new Computer(field, 'x');
        Computer computer2 = new Computer(field, 'o');
        Checker checker = new Checker(computer1, computer2);
        while (!checker.checkTheEnd(field)) {
            computer1.step();
            if (checker.checkTheEnd(field)) {
                break;
            }
            computer2.step();
        }
    }
}
