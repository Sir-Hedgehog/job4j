package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 22.12.2019
 */

public abstract class Cache {
    private static Map<Object, SoftReference> map = new HashMap<>();
    private Object content;
    private String key;

    public Cache(String key, Object content) {
        this.key = key;
        this.content = content;
        if (!map.containsKey(key)) {
            this.fill();
        }
        System.out.println(this.getKey());
    }

    /**
     * Метод сохраняет содержимое в кэш-память
     */

    private void fill() {
        SoftReference<Object> softReference;
        softReference = new SoftReference<>(content);
        map.put(key, softReference);
    }

    /**
     * Метод получает содержимое карты по ключу
     * @return - содержимое текстового файла
     */

    public Object getKey() {
        return map.get(key);
    }
}
