package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Sir-Hedgehog
 * @version $Id$
 * @since 16.09.2018
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
    public void replace(String id, Item item) {
        for (int index = 0; index < this.items.length; index++) {
            if (id != null && this.items[index].getId().equals(id)) {
                this.items[index] = item;
                break;
            }
        }
    }

    /**
     * Метод удаляет заявку
     * @param id идентификатор заявки
     */
    public void delete(String id) {
        for (int index = 0; index < this.items.length; index++) {
            if (id != null && this.items[index].getId().equals(id)) {
                System.arraycopy(this.items, index + 1, this.items, index, this.items.length - index - 1);
                break;
            }
        }
    }

    /**
     * Метод выдает список всех заявок
     * @return список заявок
     */
    public Item[] findAll() {
        Item[] result = new Item[position];
        for (int index = 0; index != position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    /**
     * Метод выдает список по имени
     * @param key вводится имя
     * @return существующая заявка по введенному имени
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[position];
        for (int index = 0; index != this.items.length; index++) {
            if (key != null && this.items[index].getName().equals(key)) {
                result[position++] = this.items[index];
            }
        }
        return Arrays.copyOf(result, position);
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
