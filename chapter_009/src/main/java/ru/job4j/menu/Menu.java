package ru.job4j.menu;

import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 4.12.2019
 */

public class Menu implements Actions {
    private List<String> heads;

    public Menu(List<String> heads) {
        this.heads = heads;
    }

    /**
     * Метод добавляет новый заголовок в зависимости от иерархии содержания
     * @param existingHead - существующий заголовок, после которого нужно добавить новый заголовок
     * @param newHead - новый заголовок
     */

    @Override
    public void add(HeadManager existingHead, HeadManager newHead) {
        for (int index = 0; index < this.heads.size(); index++) {
            if (this.heads.get(index).equals(existingHead.order())) {
                this.heads.add(index + 1, newHead.order());
            }
        }
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
