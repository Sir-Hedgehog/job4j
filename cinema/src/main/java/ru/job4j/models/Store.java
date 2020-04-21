package ru.job4j.models;

import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 05.04.2020
 */

public interface Store {
    boolean takePlace(Hall hall);
    boolean addAccount(Account account) throws SQLException;
    CopyOnWriteArrayList<Hall> findTakenPlaces();
}
