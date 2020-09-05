package ru.job4j.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 05.09.2020
 */

@RestController
@RequestMapping("/person/")
public class PersonController {
    private final PersonRepository persons;
    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    public PersonController(final PersonRepository persons) {
        this.persons = persons;
    }

    /**
     * Метод выдает список всех данных пользователей
     * @return - список всех данных пользователей
     */

    @GetMapping("/")
    public List<Person> findAll() {
        return StreamSupport.stream(this.persons.findAll().spliterator(), false).collect(Collectors.toList());
    }

    /**
     * Метод осуществляет поиск данных пользователя по идентификатору
     * @param id - идентификатор
     * @return - данные пользователя
     */

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable(value = "id") int id) {
        var person = this.persons.findById(id);
        return new ResponseEntity<>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    /**
     * Метод создает данные нового пользователя
     * @param person - данные пользователя
     * @return - успешность создания
     */

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return new ResponseEntity<>(
                this.persons.save(person),
                HttpStatus.CREATED
        );
    }

    /**
     * Метод обновляет данные пользователя
     * @param person - данные пользователя
     * @return - успешность обновления
     */

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        this.persons.save(person);
        return ResponseEntity.ok().build();
    }

    /**
     * Метод удаляет данные пользователя
     * @param id - идентификатор пользователя
     * @return - успешность удаления
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") int id) {
        Person person = new Person();
        person.setId(id);
        this.persons.delete(person);
        return ResponseEntity.ok().build();
    }
}
