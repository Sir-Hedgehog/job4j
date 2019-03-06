package ru.job4j.map;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleMapTest {
    @Test
    public void whenCreateSimpleMapThenCheckItsMethods() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertThat(map.insert(1, "Петров"), is(true));
        assertThat(map.insert(2, "Сидоров"), is(true));
        assertThat(map.insert(3, "Иванов"), is(true));
        assertThat(map.insert(4, "Морозов"), is(true));
        assertThat(map.insert(5, "Холодов"), is(true));
        assertThat(map.get(1), is("Петров"));
        assertThat(map.get(2), is("Сидоров"));
        assertThat(map.get(3), is("Иванов"));
        assertThat(map.delete(3), is(true));
        assertThat(map.delete(1), is(true));
        Iterator it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
    }
}
