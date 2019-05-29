package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 12.12.2018
 */

public class Tracker implements ITracker {
    List<Item> items = new ArrayList<>();
    private static final Random RANDOM = new Random();

    /**
     * Метод генерирует уникальный ключ для заявки
     * @return уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(RANDOM.nextInt());
    }

    /**
     * Метод реализует добавление заявки в хранилище
     * @param item новая заявка
     * @return заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    /**
     * Метод обновляет заявку
     * @param item существующая заявка
     * @param id идентификатор
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        item.setId(id);
        for (int index = 0; index < this.items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
                this.items.set(index, item);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод удаляет заявку
     * @param id идентификатор заявки
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int index = 0; index != this.items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
                result = true;
                this.items.remove(index);
                break;
            }
        }
        return result;
    }

    /**
     * Метод выдает список всех заявок
     * @return список заявок
     */
    public List<Item> findAll() {
        return this.items.subList(0, this.items.size());
    }

    /**
     * Метод выдает список по имени
     * @param key вводится имя
     * @return существующая заявка по введенному имени
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (int index = 0; index < this.items.size(); index++) {
            if (this.items.get(index).getName().equals(key)) {
                result.add(this.items.get(index));
            }
        }
        return result;
    }

    /**
     * Метод выдает список по имени
     * @param id вводится идентификатор
     * @return существующая заявка по введенному идентификатору
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
