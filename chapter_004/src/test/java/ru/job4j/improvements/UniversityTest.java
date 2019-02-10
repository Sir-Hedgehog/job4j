package ru.job4j.improvements;

import org.junit.Test;
import ru.job4j.improvements.Student;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UniversityTest {
    @Test
    public void whenExamThen() {
        University university = new University();
        Student student1 = null;
        Student student2 = new Student("Копьев", 87);
        Student student3 = new Student("Юрский", 71);
        Student student4 = new Student("Побегалов", 67);
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        List<Student> result = university.levelOf(list, 70);
        List<Student> expected = new ArrayList<>();
        expected.add(new Student("Копьев", 87));
        expected.add(new Student("Юрский", 71));
        assertThat(result, is(expected));
    }
}
