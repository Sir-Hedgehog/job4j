package ru.job4j.storage;

import java.time.LocalDate;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 23.11.2019
 */

public class Milk extends Food {
    public Milk(String name, LocalDate expireDate, LocalDate createDate, int price, double discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
