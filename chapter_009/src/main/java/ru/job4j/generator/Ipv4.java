package ru.job4j.generator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 8.12.2019
 */

public class Ipv4 {
    private final Pattern ipv4 = Pattern.compile("^(((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]))$");

    /**
     * Метод проверяет ipv4 на валидность
     * @param value - проверяемое значение
     * @return - валидный ipv4 или нет
     */

    public boolean generateIpv4(String value) {
        Matcher matcher1 = ipv4.matcher(value);
        return matcher1.find();
    }
}
