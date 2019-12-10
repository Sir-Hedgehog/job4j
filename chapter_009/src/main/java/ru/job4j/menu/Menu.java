package ru.job4j.menu;

import java.util.TreeMap;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 10.12.2019
 */

public class Menu implements Actions {
    private TreeMap<String, HeadManager> heads;

    public Menu(TreeMap<String, HeadManager> heads) {
        this.heads = heads;
    }

    /**
     * Метод добавляет новый заголовок
     * @param newHead - новый заголовок
     */

    @Override
    public void add(HeadManager newHead) {
        this.heads.put(newHead.order(), newHead);
    }

    /**
     * Метод удаляет заголовок
     * @param existingHead - заголовок
     */

    @Override
    public void delete(HeadManager existingHead) {
        this.heads.remove(existingHead.order());
    }
}
