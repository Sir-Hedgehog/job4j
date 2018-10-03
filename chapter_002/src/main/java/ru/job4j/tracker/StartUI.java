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
    Tracker tracker = new Tracker();


    /**
     * Конструтор, инициализирующий поле.
     * @param input ввод данных.
     */
    public StartUI(Input input) {
        this.input = input;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(this.input, tracker);
        List<Integer> range = new ArrayList<>();
        menu.fillActions(this);
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range.add(i);
        }
        do {
            menu.show();
            int key = Integer.valueOf(input.ask("Выберите пункт: "));
            menu.select(key);
        } while (this.working);
    }

    public void stop() {
        this.working = false;
    }

    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput()).init();
    }
}
