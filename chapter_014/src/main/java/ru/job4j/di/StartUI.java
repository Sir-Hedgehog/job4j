package ru.job4j.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 09.05.2020
 */

@Component
public class StartUI {

    @Autowired
    private Store store;

    @Autowired
    private ConsoleInput input;


    public StartUI(Store store) {
        this.store = store;
    }

    /**
     * Метод добавляет значение в список
     * @param value - значение
     */

    public void add(String value) {
        store.add(value);
    }

    /**
     * Метод печатает существующие значения
     */

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }

    /**
     * Метод задает вопрос пользователю с целью получения обратной связи
     * @param question - вопрос
     * @return - ответ
     */

    public String ask(String question) {
        return input.ask(question);
    }
}
