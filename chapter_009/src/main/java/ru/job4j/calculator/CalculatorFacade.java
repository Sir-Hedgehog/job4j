package ru.job4j.calculator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.11.2019
 */

public class CalculatorFacade {
    private final Adding adding = new Adding();
    private final Subtraction subtraction = new Subtraction();
    private final Multiplication multiplication = new Multiplication();
    private final Division division = new Division();

    /**
     * Метод определяет операцию в зависимости от знака
     * @param first - первое число
     * @param mark - операция
     * @param second - второе число
     */

    public void getResult(double first, String mark, double second) {
        if (mark.equals("+")) {
            adding.getResult(first, mark, second);
        } else if (mark.equals("-")) {
            subtraction.getResult(first, mark, second);
        } else if (mark.equals("*")) {
            multiplication.getResult(first, mark, second);
        } else if (mark.equals("/")) {
            division.getResult(first, mark, second);
        } else {
            System.out.println("If you did't get an answer, then you entered incorrect operation. Try to repeat your expression");
        }
    }
}
