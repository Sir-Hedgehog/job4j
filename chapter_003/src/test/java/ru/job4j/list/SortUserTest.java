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
}
