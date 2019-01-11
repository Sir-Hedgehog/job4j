package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Item;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 11.01.2019
 */

public enum EnumSingleton {
    SINGLETON;

    public Item add(Item model) {
        return model;
    }
}
