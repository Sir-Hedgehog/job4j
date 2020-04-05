package ru.job4j.models;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 05.04.2020
 */

public interface Validation {
    boolean choosePlace(Hall hall);
    boolean addData(Account account);
    CopyOnWriteArrayList<Hall> getTakenPlaces();
}
