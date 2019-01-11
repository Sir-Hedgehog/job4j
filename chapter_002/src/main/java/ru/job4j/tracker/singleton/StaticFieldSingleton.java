package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Item;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 11.01.2019
 */

public class StaticFieldSingleton {
    private static StaticFieldSingleton instance;

    private StaticFieldSingleton(Item item) {
    }

    public static StaticFieldSingleton getInstance() {
        if (instance == null) {
            instance = new StaticFieldSingleton(new Item("test1", "testDescription1"));
        }
        return instance;
    }

    public Item add(Item model) {
        return model;
    }
}
