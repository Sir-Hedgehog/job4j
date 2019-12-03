package ru.job4j.parking;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 3.12.2019
 */

public interface Distributor {
    boolean accept(Cars cars);
}
