package ru.job4j.stream;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 07.02.2019
 */

class School {
    List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }

    Map<String, Student> collectToMap(List<Student> students) {
        return students.stream().collect(Collectors.toMap(student -> student.getSurname(), student -> student));
    }
}
