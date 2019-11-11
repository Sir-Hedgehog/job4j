package ru.job4j.calculator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 11.11.2019
 */

public class TrigonometricCalc extends CalculatorFacade {
    Sin sin = new Sin();
    Cos cos = new Cos();
    Tg tg = new Tg();
    Ctg ctg = new Ctg();

    @Override
    public void getResult(double first, String mark, double second) {
        getTrigonometricResult(first, mark, second);
        super.getResult(first, mark, second);
    }

    public void getTrigonometricResult(double first, String mark, double second) {
        if (mark.toLowerCase().trim().equals("sin")) {
            sin.getResult(first, mark, second);
        } else if (mark.toLowerCase().trim().equals("cos")) {
            cos.getResult(first, mark, second);
        } else if (mark.toLowerCase().trim().equals("tg")) {
            tg.getResult(first, mark, second);
        } else if (mark.toLowerCase().trim().equals("ctg")) {
            ctg.getResult(first, mark, second);
        }
    }
}
