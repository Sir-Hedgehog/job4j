package ru.job4j.chat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.repository.PersonRepository;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 11.09.2020
 */

@RestController
@RequestMapping("/persons/")
public class PersonController {
    private final PersonRepository persons;
    private BCryptPasswordEncoder encoder;
    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    public PersonController(PersonRepository persons, BCryptPasswordEncoder encoder) {
        this.persons = persons;
        this.encoder = encoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        persons.save(person);
    }

    /**
     * Метод отображает весь список пользователей
     * @return - список пользователей
     */

    @GetMapping("/")
    public List<Person> findAll() {
        return this.persons.findAll();
    }

    /**
     * Метод осуществляет поиск данных пользователя по идентификатору
     * @param id - идентификатор пользователя
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
     * @return - результат создания
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
     * @return - результат обновления
     */

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        this.persons.save(person);
        return ResponseEntity.ok().build();
    }

    /**
     * Метод удаляет данные пользователя
     * @param id - идентификатор пользователя
     * @return - результат удаления
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") int id) {
        Person person = new Person();
        person.setId(id);
        this.persons.delete(person);
        return ResponseEntity.ok().build();
    }
}
