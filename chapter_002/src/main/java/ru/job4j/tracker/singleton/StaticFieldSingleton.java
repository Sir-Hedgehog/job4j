package ru.job4j.tracker.singleton;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 11.01.2019
 */

public class StaticFieldSingleton {
    private static StaticFieldSingleton instance;

    private StaticFieldSingleton() {
    }

    public static StaticFieldSingleton getInstance() {
        if (instance == null) {
            instance = new StaticFieldSingleton();
        }
        return instance;
    }
}
