package ru.job4j.menu;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 4.12.2019
 */

public interface Actions {

    void add(HeadManager existingHead, HeadManager newHead);

    void delete(HeadManager existingHead);
}
