package ru.job4j.map;

import java.util.*;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserTest {
    @Test
    public void whenRealizesTwoSameUsersThen() {
        GregorianCalendar birthday = new GregorianCalendar(1975, Calendar.DECEMBER, 31);
        User first = new User("George", 5, birthday);
        User second = new User("George", 5, birthday);
        Map<User, Object> map = new HashMap<>();
        map.put(first, 55);
        map.put(second, 55);
        System.out.println(map);
        assertThat(map.get(first), is(map.get(second)));
    }
}
