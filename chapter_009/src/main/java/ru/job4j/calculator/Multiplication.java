package ru.job4j.calculator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.11.2019
 */

public class Multiplication {

    /**
     * Метод производит умножение
     * @param first - первое число
     * @param second - второе число
     */

    public void getResult(double first, double second) {
        double result = first * second;
        System.out.println("The result: " + result);
    }
}
