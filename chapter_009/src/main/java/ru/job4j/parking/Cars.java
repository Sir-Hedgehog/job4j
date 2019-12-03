package ru.job4j.parking;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 3.12.2019
 */

public abstract class Cars {
    private String model;
    private String number;
    private double weight;

    public Cars(String model, String number, double weight) {
        this.model = model;
        this.number = number;
        this.weight = weight;
    }

    public String getModel() {
        return model;
    }

    public double getWeight() {
        return weight;
    }
}
