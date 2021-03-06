package ru.job4j.chat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repository.MessageRepository;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import static ru.job4j.chat.filter.JWTAuthenticationFilter.TOKEN_PREFIX;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 11.09.2020
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
     * Метод осуществляет поиск всех сообщений чат-комнаты после проверки токена для аутентификации пользователя
     * @param id - идентификатор чат-комнаты
     * @return - список сообщений
     */

    @GetMapping("/{id}")
    public List<Message> findByIdOfRoom(@PathVariable(value = "id") int id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("Authorization", TOKEN_PREFIX + token);
        HttpEntity<Room> requestEntity = new HttpEntity<>(requestHeaders);
        List<Message> messages = new ArrayList<>();
        List<Room> rooms = rest.exchange(
                API,
                HttpMethod.GET,
                requestEntity,
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
