package ru.job4j.calculator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 11.11.2019
 */

public class Ctg {

    /**
     * Косинус числа
     * @param first - первое число
     * @param second - первое число
     */

    public void getResult(double first, double second) {
        double result = 1 / Math.toDegrees(Math.atan(Math.tan(first / second)));
        System.out.println("The result: " + result);
    }
}
