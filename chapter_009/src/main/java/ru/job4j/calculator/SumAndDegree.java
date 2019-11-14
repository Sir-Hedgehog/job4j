package ru.job4j.calculator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 14.11.2019
 */

public class SumAndDegree {
    /**
     * Сумма и степень выражения
     * @param first - первое число
     * @param second - первое число
     * @param degree - первое число
     */

    public void getResult(double first, double second, double degree) {
        double result = (Math.pow((first + second), degree));
        System.out.println("The result: " + result);
    }
}
