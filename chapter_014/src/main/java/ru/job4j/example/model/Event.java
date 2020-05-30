package ru.job4j.example.model;

import java.util.Objects;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 29.05.2020
 */

public class Event {
    private String description;
    private String name;
    private String numberOfCar;
    private String city;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfCar() {
        return numberOfCar;
    }

    public void setNumberOfCar(String numberOfCar) {
        this.numberOfCar = numberOfCar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(description, event.description)
                && Objects.equals(name, event.name)
                && Objects.equals(numberOfCar, event.numberOfCar)
                && Objects.equals(city, event.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, name, numberOfCar, city);
    }
}
