package ru.job4j.tracker;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 07.10.2018
 */

public class ValidateInput implements Input {
    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Введите корректное значение!");
            } catch (MenuOutException moe) {
                System.out.println("Необходимо выбрать число из представленного диапазона значений!");
            }
        } while (invalid);
        return value;
    }
}
