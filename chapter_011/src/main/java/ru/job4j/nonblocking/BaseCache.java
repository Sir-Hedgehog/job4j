package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 03.02.2020
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
     * Метод обновляет версию модели в случае соответствия версий входящего параметра и существующей модели кэша
     * @param model - обновляемая модель
     */

    public void update(Base model) throws OptimisticException {
        map.computeIfPresent(model.getId(), (key, value) -> {
            if (model.getVersion() == value.getVersion()) {
                this.map.put(model.getId(), model);
                model.setVersion(value.getVersion() + 1);
                return model;
            } else {
                throw new OptimisticException();
            }
        });
    }

    /**
     * Метод удаляет элементы из кэша
     * @param model - удаляемая модель
     */

    public void delete(Base model) {
        map.computeIfPresent(model.getId(), (key, value) -> this.map.remove(model.getId()));
    }
}
