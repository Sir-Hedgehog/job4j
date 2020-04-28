package ru.job4j.carsale.models;

import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 28.04.2020
 */

public interface Validation {
    boolean validateAdd(Seller seller, Car car);
    String validateUpdate(String joinId);
    List<List<String>> validateGetData();
}
