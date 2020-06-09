package ru.job4j.models;

import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 09.06.2020
 */

public interface Store {
    boolean realizeTransaction(Hall hall, Account account) throws SQLException;
    CopyOnWriteArrayList<Hall> findTakenPlaces();
}
