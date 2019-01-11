package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Item;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 11.01.2019
 */

public class ClassSingleton {
    private ClassSingleton() {
    }

    public static ClassSingleton getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final ClassSingleton INSTANCE = new ClassSingleton();
    }

    public Item add(Item model) {
        return model;
    }
}
