package ru.job4j.calculator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 13.11.2019
 */

public class CalculatorFacade implements Result {
    private final Adding adding = new Adding();
    private final Subtraction subtraction = new Subtraction();
    private final Multiplication multiplication = new Multiplication();
    private final Division division = new Division();
    protected double first;
    String mark;
    protected double second;

    public CalculatorFacade(double first, String mark, double second) {
        this.first = first;
        this.mark = mark;
        this.second = second;
    }

    @Override
    public void getResult() {
        calculate(this.first, this.mark, this.second);
    }

    /**
     * Метод определяет операцию в зависимости от знака
     * @param first - первое число
     * @param mark - операция
     * @param second - второе число
     */
    public void calculate(double first, String mark, double second) {
        if (mark.equals("+")) {
            adding.getResult(first, second);
        } else if (mark.equals("-")) {
            subtraction.getResult(first, second);
        } else if (mark.equals("*")) {
            multiplication.getResult(first, second);
        } else if (mark.equals("/")) {
            division.getResult(first, second);
        }
    }
}
