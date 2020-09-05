package ru.job4j.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.auth.domain.Person;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 05.09.2020
 */

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
