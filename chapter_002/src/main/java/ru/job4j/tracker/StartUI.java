package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 31.01.2019
 */

public class StartUI {

    private boolean working = true;
    private final Input input;
    private final ITracker tracker;
    private final Consumer<String> output;

    /**
     * Конструтор, инициализирующий поле.
     * @param input ввод данных.
     */
    StartUI(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Основой цикл программы.
     */
    void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, this.output);
        menu.fillActions(this);
        do {
            menu.show();
            menu.select(input.ask("Выберите пункт: ", menu.fillKeys()));
        } while (this.working);
    }

    /**
     * Выход из программы.
     */
    void stop() {
        this.working = false;
    }

    /**
     * Запуск программы.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker(), System.out::println).init();
    }
}
