package ru.job4j.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.job4j.auth.domain.Employee;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.09.2020
 */

@Component
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
