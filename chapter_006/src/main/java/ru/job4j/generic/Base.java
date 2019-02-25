package ru.job4j.generic;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 22.02.2019
 */

public abstract class Base {
    private final String id;

    Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
