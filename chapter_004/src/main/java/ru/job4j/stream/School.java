package ru.job4j.stream;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 02.02.2019
 */

class School {
    List<Student> collect10A(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate -> 100 >= predicate.getScore() && predicate.getScore() >= 70).collect(Collectors.toList());
    }

    List<Student> collect10B(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate -> 70 > predicate.getScore() && predicate.getScore() >= 50).collect(Collectors.toList());
    }

    List<Student> collect10C(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate -> 50 > predicate.getScore() && predicate.getScore() >= 0).collect(Collectors.toList());
    }
}
