package ru.job4j.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.domain.Report;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.09.2020
 */

@RestController
@RequestMapping("/report")
public class ReportsController {
    private final RestTemplate rest;

    private static final String API = "http://localhost:8080/person/";
    private static final String API_ID = "http://localhost:8080/person/{id}";

    @Autowired
    public ReportsController(RestTemplate rest) {
        this.rest = rest;
    }

    /**
     * Метод выдает список всех отчетов работников через взаимосвязь с помощью restTemplate
     * @return - список всех отчетов работников
     */

    @GetMapping("/")
    public List<Report> findAll() {
        List<Report> result = new ArrayList<>();
        List<Person> persons = rest.exchange(
                API,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Person>>() { }
        ).getBody();
        for (Person person : Objects.requireNonNull(persons)) {
            Report report = Report.of(1, "First", person);
            result.add(report);
        }
        return result;
    }

    /**
     * Метод создает новую учетную запись через взаимосвязь с помощью restTemplate
     * @param person - новая учетная запись
     * @return - успешность создания
     */

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person result = rest.postForObject(API, person, Person.class);
        return new ResponseEntity<>(
                result,
                HttpStatus.CREATED
        );
    }

    /**
     * Метод обновляет учетную запись через взаимосвязь с помощью restTemplate
     * @param person - учетная запись работника
     * @return - успешность обновления
     */

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        rest.put(API, person);
        return ResponseEntity.ok().build();
    }

    /**
     * Метод удаляет учетную запись работника через взаимосвязь с помощью restTemplate
     * @param id - идентификатор работника
     * @return - успешность удаления
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }
}
