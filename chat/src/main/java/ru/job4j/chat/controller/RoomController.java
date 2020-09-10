package ru.job4j.chat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import static ru.job4j.chat.filter.JWTAuthenticationFilter.TOKEN_PREFIX;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 11.09.2020
 */

@RestController
@RequestMapping("/rooms/")
public class RoomController {
    private static final String API = "http://localhost:8080/persons/";
    private static final String API_ID = "http://localhost:8080/persons/{id}";
    private final RestTemplate rest;
    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    public RoomController(RestTemplate rest) {
        this.rest = rest;
    }

    /**
     * Метод выдает множество всех чат-комнат после проверки токена для аутентификации пользователя
     * @return - множество всех чат-комнат
     */

    @GetMapping("/")
    public Set<Room> findAll() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("Authorization", TOKEN_PREFIX + token);
        HttpEntity<Person> requestEntity = new HttpEntity<>(requestHeaders);
        Set<Room> result = new HashSet<>();
        List<Person> persons = rest.exchange(
                API,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Person>>() {}
        ).getBody();
        for (Person person : Objects.requireNonNull(persons)) {
            result.addAll(person.getRooms());
        }
        return result;
    }


    /**
     * Метод выдает множество чат-комнат, доступных для конкретного пользователя, после проверки токена для аутентификации пользователя
     * @param id - идентификатор пользователя
     * @return - множество чат-комнат
     */

    @GetMapping("/{id}")
    public Set<Room> findByIdOfPerson(@PathVariable(value = "id") int id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("Authorization", TOKEN_PREFIX + token);
        HttpEntity<Person> requestEntity = new HttpEntity<>(requestHeaders);
        Person person = rest.exchange(
                API_ID,
                HttpMethod.GET,
                requestEntity,
                Person.class, id
        ).getBody();
        return Objects.requireNonNull(person).getRooms();
    }
}
