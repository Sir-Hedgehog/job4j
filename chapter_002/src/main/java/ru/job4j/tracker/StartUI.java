package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 01.10.2018
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
        List<Integer> range = new ArrayList<>();
        menu.fillActions(this);
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range.add(i);
        }
        do {
            menu.show();
            //int key = Integer.valueOf(input.ask("Выберите пункт: "));
            menu.select(input.ask("Выберите пункт: ", new int[range.size()]));
        } while (this.working);
    }
    public void stop() {
        this.working = false;
    }

    /**
     * Запуск программы.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}
