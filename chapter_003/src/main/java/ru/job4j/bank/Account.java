package ru.job4j.bank;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 22.01.2019
 */

public class Account {
    private double value;
    private String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void minus(double amount) {
        this.value -= amount;
    }

    public void plus(double amount) {
        this.value += amount;
    }

    @Override
    public String toString() {
        return "Account {" +
                "value = " + value +
                ", requisites = '" + requisites + '\'' +
                '}';
    }
}
