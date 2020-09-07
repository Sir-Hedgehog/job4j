package ru.job4j.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.domain.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 07.09.2020
 */

@RestController
@RequestMapping("/person/")
public class PersonController {
    private static final String API = "http://localhost:8080/employee/";
    private static final String API_ID = "http://localhost:8080/employee/{id}";
    private final RestTemplate rest;
    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    public PersonController(final RestTemplate rest) {
        this.rest = rest;
    }

    /**
     * Метод выдает список всех учетных записей работников через взаимосвязь с помощью restTemplate
     * @return - список всех учетных записей работников
     */

    @GetMapping("/")
    public List<Person> findAll() {
        List<Person> result = new ArrayList<>();
        List<Employee> employees = rest.exchange(
                API,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {}
        ).getBody();
        for (Employee employee : Objects.requireNonNull(employees)) {
            result.addAll(employee.getPersons());
        }
        return result;
    }

    /**
     * Метод создает нового работника через взаимосвязь с помощью restTemplate
     * @param employee - новый работник
     * @return - успешность создания
     */

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee result = rest.postForObject(API, employee, Employee.class);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * Метод обновляет данные работника через взаимосвязь с помощью restTemplate
     * @param employee - данные работника
     * @return - успешность обновления
     */

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Employee employee) {
        rest.put(API, employee);
        return ResponseEntity.ok().build();
    }

    /**
     * Метод удаляет данные уволенного работника через взаимосвязь с помощью restTemplate
     * @param id - идентификатор работника
     * @return - успешность удаления
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }
}
