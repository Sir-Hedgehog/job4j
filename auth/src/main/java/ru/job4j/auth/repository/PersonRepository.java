package ru.job4j.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.job4j.auth.domain.Person;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 07.09.2020
 */

@Component
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
