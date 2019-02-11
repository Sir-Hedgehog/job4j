package ru.job4j.stream;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {
    @Test
    public void whenCollect10AThen() {
        School school = new School();
        List<Student> list = new ArrayList<>();
        list.add(new Student(76));
        list.add(new Student(98));
        list.add(new Student(50));
        List<Student> result = school.collect(list, predicate  -> predicate.getScore() >= 70 && predicate.getScore() <= 100);
        List<Student> expected = List.of(
                new Student(76),
                new Student(98));
        assertThat(result, is(expected));
    }

    @Test
    public void whenCollect10BThen() {
        School school = new School();
        List<Student> list = new ArrayList<>();
        list.add(new Student(15));
        list.add(new Student(56));
        list.add(new Student(65));
        List<Student> result = school.collect(list, predicate -> predicate.getScore() < 70 && predicate.getScore() >= 50);
        List<Student> expected = List.of(
                new Student(56),
                new Student(65));
        assertThat(result, is(expected));
    }

    @Test
    public void whenCollect10CThen() {
        School school = new School();
        List<Student> list = new ArrayList<>();
        list.add(new Student(15));
        list.add(new Student(56));
        list.add(new Student(5));
        List<Student> result = school.collect(list, predicate -> predicate.getScore() >= 0 && predicate.getScore() < 50);
        List<Student> expected = List.of(
                new Student(15),
                new Student(5));
        assertThat(result, is(expected));
    }

    @Test
    public void whenCollectListToMapThen() {
        School school = new School();
        List<Student> list = new ArrayList<>();
        list.add(new Student("Петров"));
        list.add(new Student("Иванов"));
        list.add(new Student("Сидоров"));
        Map<String, Student> result = school.collectToMap(list);
        Map<String, Student> expected = Map.of(
                "Петров", new Student(),
                "Иванов", new Student(),
                "Сидоров", new Student());
        assertThat(result, is(expected));
    }
}
