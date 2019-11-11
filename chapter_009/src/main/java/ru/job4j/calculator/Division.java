package ru.job4j.calculator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 07.11.2019
 */

public class Division {

    /**
     * Метод производит деление
     * @param first- неотсортированный список скриптов
     * @param mark- неотсортированный список скриптов
     * @param second - неотсортированный список скриптов
     */

    public void getResult(double first, String mark, double second) {
        double result = first / second;
        System.out.println("The result: " + result);
    }
}
