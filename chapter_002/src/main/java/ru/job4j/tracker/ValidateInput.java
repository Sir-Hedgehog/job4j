package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Необходимо выбрать число из представленного диапазона значений!");
            } catch (NumberFormatException nfe) {
                System.out.println("Введите корректное значение!");
            }
        } while (invalid);
        return value;
    }
}
