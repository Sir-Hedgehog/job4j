package ru.job4j.bank;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 17.01.2019
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

    public boolean transfer(Account destination, double source) {
        boolean result = false;
        if (source > 0 && source < this.value && destination != null) {
            this.value -= source;
            destination.value += source;
            result = true;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Account {" +
                "value = " + value +
                ", requisites = '" + requisites + '\'' +
                '}';
    }
}
