package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 30.01.2020
 */

public class BaseCache {
    private ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    /**
     * Метод добавляет новые модели в кэш
     * @param model - новая модель
     */

    public void add(Base model) {
        this.map.put(model.getId(), model);
    }

    /**
     * Метод обновляет версию модели в случае обновления имени пользователя
     * @param model - обновляемая модель
     */

    public void update(Base model) {
        map.computeIfPresent(model.getId(), (key, value) -> this.map.put(model.getId(), model));
        model.setVersion(new AtomicInteger(model.getVersion().incrementAndGet()));
    }

    /**
     * Метод удаляет элементы из кэша
     * @param model - удаляемая модель
     */

    public void delete(Base model) {
        map.computeIfPresent(model.getId(), (key, value) -> this.map.remove(model.getId()));
    }
}
