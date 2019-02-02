package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {
    @Test
    public void whenCollect10AThen() {
        School school = new School();
        List<Student> list = new ArrayList<>();
        list.add(new  Student(76));
        list.add(new Student(98));
        list.add(new Student(50));
        List<Student> result = school.collect10A(list, predicate  -> true);
        List<Student> expected = new ArrayList<>();
        expected.add(new Student(76));
        expected.add(new Student(98));
        assertThat(result, is(expected));
    }

    @Test
    public void whenCollect10BThen() {
        School school = new School();
        List<Student> list = new ArrayList<>();
        list.add(new  Student(15));
        list.add(new Student(56));
        list.add(new Student(65));
        List<Student> result = school.collect10B(list, predicate -> true);
        List<Student> expected = new ArrayList<>();
        expected.add(new Student(56));
        expected.add(new Student(65));
        assertThat(result, is(expected));
    }

    @Test
    public void whenCollect10CThen() {
        School school = new School();
        List<Student> list = new ArrayList<>();
        list.add(new  Student(15));
        list.add(new Student(56));
        list.add(new Student(5));
        List<Student> result = school.collect10C(list, predicate -> true);
        List<Student> expected = new ArrayList<>();
        expected.add(new Student(15));
        expected.add(new Student(5));
        assertThat(result, is(expected));
    }
}
