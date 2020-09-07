package ru.job4j.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.repository.EmployeeRepository;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.09.2020
 */

@RestController
@RequestMapping("/employee/")
public class EmployeeController {
    private final EmployeeRepository employees;
    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    public EmployeeController(final EmployeeRepository employees) {
        this.employees = employees;
    }

    /**
     * Метод отображает весь список работников
     * @return - список работников
     */

    @GetMapping("/")
    public List<Employee> findAll() {
        return StreamSupport.stream(this.employees.findAll().spliterator(), false).collect(Collectors.toList());
    }

    /**
     * Метод осуществляет поиск данных работника по идентификатору
     * @param id - идентификатор
     * @return - данные работника
     */

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable(value = "id") int id) {
        var employee = this.employees.findById(id);
        return new ResponseEntity<>(
                employee.orElse(new Employee()),
                employee.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    /**
     * Метод создает данные нового работника
     * @param employee - данные работника
     * @return - успешность создания
     */

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return new ResponseEntity<>(
                this.employees.save(employee),
                HttpStatus.CREATED
        );
    }

    /**
     * Метод обновляет данные работника
     * @param employee - данные работника
     * @return - успешность обновления
     */

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Employee employee) {
        this.employees.save(employee);
        return ResponseEntity.ok().build();
    }

    /**
     * Метод удаляет данные уволенного работника
     * @param id - идентификатор работника
     * @return - успешность удаления
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") int id) {
        Employee employee = new Employee();
        employee.setId(id);
        this.employees.delete(employee);
        return ResponseEntity.ok().build();
    }
}
