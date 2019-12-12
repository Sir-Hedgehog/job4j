package ru.job4j.tictactoe;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 12.12.2019
 */

public class Checker {
    private int calculator = -1;

    /**
     * Метод проверяет наступление окончания игры
     * @param field - состояния поля
     * @return - наступило окончание игры или нет
     */

    public boolean checkTheEnd(Field field) {
        boolean result = false;
        calculator++;
        if (checkWin(field) && field.getMark() == 'x') {
            System.out.println("И перед нами победитель! Пользователь умнее компьютера!");
            result = true;
        } else if (checkWin(field) && field.getMark() == 'o') {
            System.out.println("И перед нами победитель! Скайнет умнее человека!");
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
        for (int i = 0; i < field.getArray().length; i++) {
            horizon = 0; vertical = 0;
            for (int j = 0; j < field.getArray().length; j++) {
                if (field.getArray()[i][j] == field.getMark()) {
                    horizon++;
                }
                if (field.getArray()[j][i] == field.getMark()) {
                    vertical++;
                }
            }
            if (horizon == field.getArray().length || vertical == field.getArray().length) {
                result = true;
            }
        }
        diagonal1 = 0; diagonal2 = 0;
        for (int i = 0; i < field.getArray().length; i++) {
            if (field.getArray()[i][i] == field.getMark()) {
                diagonal1++;
            }
            if (field.getArray()[i][field.getArray().length - i - 1] == field.getMark()) {
                diagonal2++;
            }
        }
        if (diagonal1 == field.getArray().length || diagonal2 == field.getArray().length) {
            result = true;
        }
        return result;
    }
}
