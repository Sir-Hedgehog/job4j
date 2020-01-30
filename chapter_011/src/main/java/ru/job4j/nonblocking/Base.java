package ru.job4j.nonblocking;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 30.01.2020
 */

public class Base {
    private int id;
    private AtomicInteger version = new AtomicInteger(0);
    private String name;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
