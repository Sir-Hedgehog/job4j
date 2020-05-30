package ru.job4j.example.service;

import ru.job4j.example.model.Event;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 29.05.2020
 */

public class EventService {
    private List<Event> events = new ArrayList<>();
    private static final EventService INSTANCE = new EventService();

    /**
     * Метод дает право создать единственный экземпляр класса для взаимосвязи с контроллером
     * @return - экземпляр класса EventService
     */

    public static EventService getInstance() {
        return INSTANCE;
    }

    /**
     * Метод получает данные из хранилища
     * @return - данные из хранилища
     */

    public List<Event> getData() {
        this.imitateData();
        return events;
    }

    /**
     * Метод имитирует сохранение данных в хранилище
     */

    private void imitateData() {
        Event event1 = new Event();
        event1.setDescription("Превышение скорости");
        event1.setName("Вуди Аллен");
        event1.setNumberOfCar("а345во");
        event1.setCity("Париж");
        Event event2 = new Event();
        event2.setDescription("Проезд на красный свет");
        event2.setName("Златан Ибрагимович");
        event2.setNumberOfCar("м701ук");
        event2.setCity("Стокгольм");
        Event event3 = new Event();
        event3.setDescription("Пересечение двойной сплошной");
        event3.setName("Наполеон Бонапарт");
        event3.setNumberOfCar("о700оо");
        event3.setCity("Марсель");
        Event event4 = new Event();
        event4.setDescription("Не пристегнул ремень безопасности");
        event4.setName("Дон Кихот");
        event4.setNumberOfCar("н690ке");
        event4.setCity("Мадрид");
        Event event5 = new Event();
        event5.setDescription("Управление авто в нетрезвом виде");
        event5.setName("Кристофор Колумб");
        event5.setNumberOfCar("м123уу");
        event5.setCity("Мехико");
        events = List.of(event1, event2, event3, event4, event5);
    }
}
