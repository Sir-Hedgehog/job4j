package ru.job4j.calculator;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 13.11.2019
 */

public class EngineeringCalc extends CalculatorFacade {
    private final Sin sin = new Sin();
    private final Cos cos = new Cos();
    private final Tg tg = new Tg();
    private final Ctg ctg = new Ctg();

    public EngineeringCalc(double first, String mark, double second) {
        super(first, mark, second);
    }

    @Override
    public void getResult() {
        calculateTrigonometry(first, mark, second);
        super.getResult();
    }

    /**
     * Метод определяет тригонометрическую операцию
     * @param first - первое число
     * @param mark - операция
     * @param second - второе число
     */

    public void calculateTrigonometry(double first, String mark, double second) {
        if (mark.toLowerCase().trim().equals("sin")) {
            sin.getResult(first, second);
        } else if (mark.toLowerCase().trim().equals("cos")) {
            cos.getResult(first, second);
        } else if (mark.toLowerCase().trim().equals("tg")) {
            tg.getResult(first, second);
        } else if (mark.toLowerCase().trim().equals("ctg")) {
            ctg.getResult(first, second);
        }
    }
}
