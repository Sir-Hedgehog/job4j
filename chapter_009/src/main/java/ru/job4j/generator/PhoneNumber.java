package ru.job4j.generator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 8.12.2019
 */

public class PhoneNumber {
    private final Pattern phoneNumber = Pattern.compile("^((8|\\+7)(-?)(9\\d\\d)(-?)(\\d{3})(-?)(\\d{2})(-?)(\\d{2}))$");

    /**
     * Метод проверяет номер телефона на валидность
     * @param value - проверяемое значение
     * @return - валидный номер или нет
     */

    public boolean generateNumber(String value) {
        Matcher matcher1 = phoneNumber.matcher(value);
        return matcher1.find();
    }
}
