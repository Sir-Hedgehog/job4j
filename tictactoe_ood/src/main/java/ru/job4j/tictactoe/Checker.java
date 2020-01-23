package ru.job4j.tictactoe;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 16.12.2019
 */

public class Checker {
    private int calculator = -1;
    private Player player1;
    private Player player2;

    public Checker(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Метод проверяет наступление окончания игры
     * @param field - состояние поля
     * @return - наступило окончание игры или нет
     */

    public boolean checkTheEnd(Field field) {
        boolean result = false;
        calculator++;
        if (checkWin(field) && field.getMark() == player1.getMark()) {
            System.out.println("И перед нами победитель! " + player1.getName() + " - чемпион!");
            result = true;
        } else if (checkWin(field) && field.getMark() == player2.getMark()) {
            System.out.println("И перед нами победитель! " + player2.getName() + " - чемпион!");
            result = true;
        } else if (field.getArray().length * field.getArray().length == calculator) {
            System.out.println("Игра окончена! Победила дружба!");
            result = true;
        }
        return result;
    }

    /**
     * Метод проверяет, выиграл ли кто-нибудь из игроков
     * @param field - состояние поля
     * @return - да или нет
     */

    private boolean checkWin(Field field) {
        boolean result = false;
        int diagonal1, diagonal2, horizon, vertical;
        for (int out = 0; out < field.getArray().length; out++) {
            horizon = 0; vertical = 0;
            for (int in = 0; in < field.getArray().length; in++) {
                if (field.getArray()[out][in] == field.getMark()) {
                    horizon++;
                }
                if (field.getArray()[in][out] == field.getMark()) {
                    vertical++;
                }
            }
            if (horizon == field.getArray().length || vertical == field.getArray().length) {
                result = true;
            }
        }
        diagonal1 = 0; diagonal2 = 0;
        for (int index = 0; index < field.getArray().length; index++) {
            if (field.getArray()[index][index] == field.getMark()) {
                diagonal1++;
            }
            if (field.getArray()[index][field.getArray().length - index - 1] == field.getMark()) {
                diagonal2++;
            }
        }
        if (diagonal1 == field.getArray().length || diagonal2 == field.getArray().length) {
            result = true;
        }
        return result;
    }
}
