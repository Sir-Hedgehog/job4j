package ru.job4j.generator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 9.12.2019
 */

public class SimpleGenerator implements Template {
    private final Pattern basicKey = Pattern.compile("^(\\$\\{\\w+})$");

    /**
     * Метод по указанным ключам вставляет в текст соответствующие им слова
     * @param text - исходный текст
     * @param data - карта с ключами и соответствующими им словами
     * @return результирующий текст
     */

    @Override
    public String generate(String text, Map<String, String> data) throws NoKeysException, ExtraKeysException {
        Set<String> usedKeys = new HashSet<>();
        for (Map.Entry<String, String> current : data.entrySet()) {
            Matcher matcherOfKey = basicKey.matcher(current.getKey());
            if (text.contains(current.getKey()) && matcherOfKey.find()) {
                text = text.replace(current.getKey(), current.getValue());
                usedKeys.add(current.getKey());
            }
        }
        if (data.size() > usedKeys.size()) {
            throw new ExtraKeysException("There are extra keys!");
        }
        if (text.contains("${")) {
            throw new NoKeysException("The necessary keys are absent!");
        }
        return text;
    }
}
