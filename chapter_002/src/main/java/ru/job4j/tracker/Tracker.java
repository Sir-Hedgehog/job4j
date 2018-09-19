package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 19.09.2018
 */

public class Tracker {
    private final Item[] items = new Item[100];
    private int position = 0;
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
        this.items[position++] = item;
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
        Item[] myItem = new Item[position];
        for (int index = 0; index != position; index++) {
            if (id != null && this.items[index].getId().equals(id)) {
                this.items[index] = item;
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
        Item[] myItem = new Item[position];
        for (int index = 0; index != position; index++) {
            if (id != null && this.items[index].getId().equals(id)) {
                System.arraycopy(this.items, index + 1, this.items, index, this.items.length - index - 1);
                result = true;
                position--;
                break;
            }
        }
        return result;
    }

    /**
     * Метод выдает список всех заявок
     * @return список заявок
     */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.position);
    }

    /**
     * Метод выдает список по имени
     * @param key вводится имя
     * @return существующая заявка по введенному имени
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[position];
        int out = 0;
        for (int index = 0; index != this.position; index++) {
            if (key != null && this.items[index].getName().equals(key)) {
                result[out++] = this.items[index];
            }
        }
        return Arrays.copyOf(result, out);
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
