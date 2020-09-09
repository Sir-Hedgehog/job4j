package ru.job4j.chat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 10.09.2020
 */

@RestController
@RequestMapping("/rooms/")
public class RoomController {
    private static final String API = "http://localhost:8080/persons/";
    private static final String API_ID = "http://localhost:8080/persons/{id}";
    private final RestTemplate rest;
    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    public RoomController(final RestTemplate rest) {
        this.rest = rest;
    }

    /**
     * Метод выдает множество всех чат-комнат
     * @return - множество всех чат-комнат
     */

    @GetMapping("/")
    public Set<Room> findAll() {
        Set<Room> result = new HashSet<>();
        List<Person> persons = rest.exchange(
                API,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Person>>() {}
        ).getBody();
        for (Person person : Objects.requireNonNull(persons)) {
            result.addAll(person.getRooms());
        }
        return result;
    }

    /**
     * Метод выдает множество чат-комнат, доступных для конкретного пользователя
     * @param id - идентификатор пользователя
     * @return - множество чат-комнат
     */

    @GetMapping("/{id}")
    public Set<Room> findByIdOfPerson(@PathVariable(value = "id") int id) {
        Person person = rest.exchange(
                API_ID,
                HttpMethod.GET,
                null,
                Person.class, id
        ).getBody();
        return Objects.requireNonNull(person).getRooms();
    }
}
