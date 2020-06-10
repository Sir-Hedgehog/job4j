package ru.job4j.models;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.0
 * @since 10.06.2020
 */

public interface Store {
    boolean realizeTransaction(Hall hall, Account account) throws SQLException;
    List<Hall> findTakenPlaces();
}
