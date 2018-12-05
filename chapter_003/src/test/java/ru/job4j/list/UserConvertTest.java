package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void whenConvertThen() {
        UserConvert convert = new UserConvert();
        List<User> list = new ArrayList<>();
        list.add(new User(128, "Даниил", "Москва"));
        list.add(new User(235, "Людмила", "Кемерово"));
        list.add(new User(567, "Афанасий", "Омск"));
        HashMap<Integer, User> result = convert.process(list);
        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(128, new User(128, "Даниил", "Москва"));
        expect.put(235, new User(235, "Людмила", "Кемерово"));
        expect.put(567, new User(567, "Афанасий", "Омск"));
        assertThat(result, is(expect));
    }
}
