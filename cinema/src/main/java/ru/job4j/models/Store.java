package ru.job4j.models;

import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 22.04.2020
 */

public interface Store {
    boolean takePlace(Hall hall) throws SQLException;
    boolean addAccount(Account account) throws SQLException;
    CopyOnWriteArrayList<Hall> findTakenPlaces();
}
