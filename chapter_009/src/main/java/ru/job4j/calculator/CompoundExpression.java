package ru.job4j.calculator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 14.11.2019
 */

public class CompoundExpression extends CalculatorFacade {
    private final SumAndDegree sumAndDegree = new SumAndDegree();
    private double degree;

    public CompoundExpression(double first, String mark, double second, double degree) {
        super(first, mark, second);
        this.degree = degree;
    }

    @Override
    public void getResult() {
        sumAndDegree.getResult(first, second, degree);
    }
}
