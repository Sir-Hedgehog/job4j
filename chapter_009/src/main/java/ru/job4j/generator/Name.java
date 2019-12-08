package ru.job4j.generator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 8.12.2019
 */

public class Name {
    private final Pattern name = Pattern.compile("^(([A-Z]|[А-Я])([a-z]+|[а-я]+))$");

    /**
     * Метод проверяет имя на валидность
     * @param value - проверяемое значение
     * @return - валидное имя или нет
     */

    public boolean generateName(String value) {
        Matcher matcher1 = name.matcher(value);
        return matcher1.find();
    }
}
