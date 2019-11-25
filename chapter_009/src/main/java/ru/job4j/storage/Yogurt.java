package ru.job4j.storage;

import java.time.LocalDate;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 25.11.2019
 */

public class Yogurt extends Food {
    public Yogurt(String name, LocalDate expireDate, LocalDate createDate, int price, double discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
