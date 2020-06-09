package ru.job4j.models;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 09.06.2020
 */

public interface Validation {
    boolean validateTransaction(Hall hall, Account account);
    CopyOnWriteArrayList<Hall> getTakenPlaces();
}
