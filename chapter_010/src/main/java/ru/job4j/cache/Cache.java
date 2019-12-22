package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 22.12.2019
 */

public abstract class Cache {
    private static List<SoftReference> result = new ArrayList<>();
    private static Set<Object> current = new HashSet<>();
    private Object content;
    private SoftReference<Object> softReference;

    public Cache(Object content) {
        this.content = content;
        if (!current.contains(content)) {
            current.add(content);
            this.fill();
            System.out.println(this.softReference);
        } else {
            System.out.println("Данный объект уже существует!");
        }
    }

    /**
     * Метод сохраняет содержимое в кэш-память
     */

    private void fill() {
        softReference = new SoftReference<>(content);
        result.add(softReference);
    }

    /**
     * Метод получает содержимое карты по ключу
     * @return - содержимое текстового файла
     */

    public Object getCache() {
        return softReference;
    }
}
