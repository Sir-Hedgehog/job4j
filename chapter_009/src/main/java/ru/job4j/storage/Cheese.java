package ru.job4j.storage;

import java.time.LocalDate;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.0
 * @since 27.11.2019
 */

public class Cheese extends Food {
    public Cheese(String name, LocalDate expireDate, LocalDate createDate, int price, double discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
