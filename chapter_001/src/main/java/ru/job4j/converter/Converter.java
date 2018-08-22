package ru.job4j.converter;

/**
 * Корвертор валюты.
 */

public class Converter {
    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return евро.
     */
    public int rubleToEuro(int value) {
        value /= 70;
        return value;
    }

    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return доллары
     */
    public int rubleToDollar(int value) {
        value /= 60;
        return value;
    }

    /**
     * Конвертируем евро в рубли.
     * @param value евро.
     * @return рубли.
     */
    public int euroToRuble(int value) {
        value *= 70;
        return value;
    }

    /**
     * Конвертируем доллары в рубли.
     * @param value доллары.
     * @return рубли
     */
    public int dollarToRuble(int value) {
        value *= 60;
        return value;
    }
}
