package ru.job4j.optimization;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 14.07.2019
 */

public class Entry {
    private int number;

    public Entry(int number) {
        this.number = number;
    }

    public Entry() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
