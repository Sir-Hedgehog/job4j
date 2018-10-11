package ru.job4j.tracker;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 11.10.2018
 */

public class StartUI {

    private boolean working = true;
    private final Input input;
    private final Tracker tracker;

    /**
     * Конструтор, инициализирующий поле.
     * @param input ввод данных.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);
        do {
            menu.show();
            menu.select(input.ask("Выберите пункт: ", menu.fillKeys()));
        } while (this.working);
    }

    /**
     * Выход из программы.
     */
    public void stop() {
        this.working = false;
    }

    /**
     * Запуск программы.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}
