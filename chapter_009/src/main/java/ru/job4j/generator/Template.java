package ru.job4j.generator;

import java.util.Map;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 29.11.2019
 */

public interface Template {
    String generate(String text, Map<String, String> data) throws NoKeysException, NotCorrectFormatException, ExtraKeysException;
}
