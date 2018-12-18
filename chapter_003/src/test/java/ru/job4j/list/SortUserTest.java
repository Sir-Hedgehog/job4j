package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
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
        TreeSet<User> result = user.sort(list);
        TreeSet<User> expect = new TreeSet<>();
        expect.add(new User("Даниил", 35));
        expect.add(new User("Людмила", 18));
        expect.add(new User("Афанасий", 16));
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
        List<User> expect = new ArrayList<>();
        expect.add(new User("Анастасия", 25));
        expect.add(new User("Василий", 49));
        expect.add(new User("Михаил", 39));
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
        List<User> expect = new ArrayList<>();
        expect.add(new User("Анастасия", 27));
        expect.add(new User("Анастасия", 25));
        expect.add(new User("Василий", 49));
        expect.add(new User("Михаил", 39));
        assertThat(result, is(expect));
    }
}
