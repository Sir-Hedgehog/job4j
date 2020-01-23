package ru.job4j.tictactoe;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 16.12.2019
 */

public class TictactoeTest2 {

    /**
     * Игра пользователя с компьютером
     */

    public static void main(String[] args) {
        Field field = new Field(3);
        field.paintField();
        Computer computer = new Computer(field, 'x');
        User user = new User(field, 'o');
        user.setName();
        Checker checker = new Checker(user, computer);
        while (!checker.checkTheEnd(field)) {
            computer.step();
            if (checker.checkTheEnd(field)) {
                break;
            }
            user.setCoordinates();
            user.step(user.getX(), user.getY());
        }
    }
}
