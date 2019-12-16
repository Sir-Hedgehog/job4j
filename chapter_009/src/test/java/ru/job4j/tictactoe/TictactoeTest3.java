package ru.job4j.tictactoe;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 16.12.2019
 */

public class TictactoeTest3 {

    /**
     * Игра двух пользователей
     */

    public static void main(String[] args) {
        Field field = new Field(3);
        field.paintField();
        User user1 = new User(field, 'x');
        user1.setName();
        User user2 = new User(field, 'o');
        user2.setName();
        Checker checker = new Checker(user1, user2);
        while (!checker.checkTheEnd(field)) {
            user1.setCoordinates();
            user1.step(user1.getX(), user1.getY());
            if (checker.checkTheEnd(field)) {
                break;
            }
            user2.setCoordinates();
            user2.step(user2.getX(), user2.getY());
        }
    }
}
