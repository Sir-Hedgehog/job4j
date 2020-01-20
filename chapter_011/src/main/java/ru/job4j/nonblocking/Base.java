package ru.job4j.nonblocking;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 20.01.2020
 */

public class Base {
    private int id;
    private AtomicInteger version = new AtomicInteger(0);

    public Base(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public AtomicInteger getVersion() {
        return version;
    }

    public void setVersion(AtomicInteger version) {
        this.version = version;
    }
}
