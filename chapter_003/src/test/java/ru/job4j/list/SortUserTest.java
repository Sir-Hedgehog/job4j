package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenConvertThen() {
        SortUser user = new SortUser();
        List<User> list = new ArrayList<>();
        list.add(new User("Даниил", 35));
        list.add(new User("Людмила", 18));
        list.add(new User("Афанасий", 16));
        Set<User> result = user.sort(list);
        Set<User> expect = Set.of(
                new User("Даниил", 35),
                new User("Людмила", 18),
                new User("Афанасий", 16));
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortNameLengthThen() {
        SortUser user = new SortUser();
        List<User> list = new ArrayList<>();
        list.add(new User("Михаил", 39));
        list.add(new User("Василий", 49));
        list.add(new User("Анастасия", 25));
        List<User> result = user.sortNameLength(list);
        List<User> expect = List.of(
                new User("Анастасия", 25),
                new User("Василий", 49),
                new User("Михаил", 39));
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortNameAfterThatAge() {
        SortUser user = new SortUser();
        List<User> list = new ArrayList<>();
        list.add(new User("Михаил", 39));
        list.add(new User("Василий", 49));
        list.add(new User("Анастасия", 27));
        list.add(new User("Анастасия", 25));
        List<User> result = user.sortByAllFields(list);
        List<User> expect = List.of(
                new User("Анастасия", 27),
                new User("Анастасия", 25),
                new User("Василий", 49),
                new User("Михаил", 39));
        assertThat(result, is(expect));
    }
}
