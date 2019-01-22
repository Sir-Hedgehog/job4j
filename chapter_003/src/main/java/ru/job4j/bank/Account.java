package ru.job4j.bank;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 22.01.2019
 */

public class Account {
    private double value;
    private String requisites;

    Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    double getValue() {
        return value;
    }

    String getRequisites() {
        return requisites;
    }

    void minus(double amount) {
        this.value -= amount;
    }

    void plus(double amount) {
        this.value += amount;
    }

    @Override
    public String toString() {
        return "Account {value = "
                + value
                + ", requisites = '" + requisites + '\''
                + '}';
    }
}
