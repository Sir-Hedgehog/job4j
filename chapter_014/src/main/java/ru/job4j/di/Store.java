package ru.job4j.di;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 08.05.2020
 */

@Component
@Scope("prototype")
public class Store {
    private List<String> data = new ArrayList<>();

    /**
     * Метод добавляет значение в список
     * @param value - значение
     */

    public void add(String value) {
        data.add(value);
    }

    /**
     * Метод печатает существующие значения
     */

    public List<String> getAll() {
        return data;
    }
}
