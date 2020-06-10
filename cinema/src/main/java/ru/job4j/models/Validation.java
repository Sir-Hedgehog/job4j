package ru.job4j.models;

import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 10.06.2020
 */

public interface Validation {
    boolean validateTransaction(Hall hall, Account account);
    List<Hall> getTakenPlaces();
}
