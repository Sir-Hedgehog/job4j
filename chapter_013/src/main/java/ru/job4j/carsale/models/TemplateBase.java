package ru.job4j.carsale.models;

import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 28.04.2020
 */

public interface TemplateBase {
    boolean saveAd(Seller seller, Car car);
    String updateStatus(String joinId);
    List<List<String>> getData();
}
