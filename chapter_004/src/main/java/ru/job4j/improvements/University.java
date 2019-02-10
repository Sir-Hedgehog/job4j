package ru.job4j.improvements;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class University {

    List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .flatMap((Stream::ofNullable))
                .takeWhile(result -> result.getScope() >= bound && result.getScope() <= 100)
                .collect(Collectors.toList());
    }
}
