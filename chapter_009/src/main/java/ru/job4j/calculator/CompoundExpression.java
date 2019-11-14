package ru.job4j.calculator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 14.11.2019
 */

public class CompoundExpression implements Result {
    private final SumAndDegree sumAndDegree = new SumAndDegree();
    private double first;
    private String mark;
    private double second;
    private double degree;


    public CompoundExpression(double first, String mark, double second, double degree) {
        this.first = first;
        this.mark = mark;
        this.second = second;
        this.degree = degree;
    }

    @Override
    public void getResult() {
        sumAndDegree.getResult(first, second, degree);
    }
}
