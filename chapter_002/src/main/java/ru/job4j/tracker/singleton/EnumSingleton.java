package ru.job4j.tracker.singleton;


import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 11.01.2019
 */

public enum EnumSingleton {
    SINGLETON(new Item("test1", "description1"));

    EnumSingleton(Item item) {
    }

    public Item add(Item model) {
        return model;
    }
}
