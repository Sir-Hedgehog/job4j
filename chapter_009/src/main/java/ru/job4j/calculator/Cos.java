package ru.job4j.calculator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 11.11.2019
 */

public class Cos {

    /**
     * Косинус числа
     * @param first - первое число
     * @param mark - операция
     * @param second - второе число
     */

    public void getResult(double first, String mark, double second) {
        double result = Math.toDegrees(Math.acos(Math.cos(first / second)));
        System.out.println("The result: " + result);
    }
}
