package ru.job4j.chat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repository.MessageRepository;
import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 10.09.2020
 */

@RestController
@RequestMapping("/messages/")
public class MessageController {
    private final MessageRepository messages;
    private final RestTemplate rest;
    private static final String API = "http://localhost:8080/rooms/";
    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    public MessageController(final RestTemplate rest, final MessageRepository messages) {
        this.rest = rest;
        this.messages = messages;
    }

    /**
     * Метод осуществляет поиск всех сообщений чат-комнаты
     * @param id - идентификатор чат-комнаты
     * @return - список сообщений
     */

    @GetMapping("/{id}")
    public List<Message> findByIdOfRoom(@PathVariable(value = "id") int id) {
        List<Message> messages = new ArrayList<>();
        List<Room> rooms = rest.exchange(
                API,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Room>>() {}
        ).getBody();
        for (Room elect : Objects.requireNonNull(rooms)) {
            if (id == elect.getId()) {
                messages = elect.getMessages();
            }
        }
        return messages;
    }

    /**
     * Метод создает сообщение в чат-комнате
     * @param message - новое сообщение
     * @return - результат создания нового сообщения
     */

    @PostMapping("/")
    public ResponseEntity<Message> create(@RequestBody Message message) {
        return new ResponseEntity<>(this.messages.save(message), HttpStatus.CREATED);
    }
}
