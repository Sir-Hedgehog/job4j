package ru.job4j.tracker.singleton;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 11.01.2019
 */

public class StaticFinalFieldSingleton {
    private static final StaticFinalFieldSingleton INSTANCE = new StaticFinalFieldSingleton();

    private StaticFinalFieldSingleton() {
    }

    public static StaticFinalFieldSingleton getInstance() {
        return INSTANCE;
    }

}
