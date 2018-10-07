package ru.job4j.tracker;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 22.09.2018
 */

public class StubInput implements Input {
    private final String[] value;
    private int position;

    public StubInput(final String[] value) {
        this.value = value;
    }

    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

    @Override
    public int ask(String question, int[] range) {
        throw new UnsupportedOperationException("Неверная операция!");
    }
}
