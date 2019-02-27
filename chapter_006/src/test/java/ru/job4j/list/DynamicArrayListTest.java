package ru.job4j.list;

import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DynamicArrayListTest {

    @Test
    public void whenAddManyIntegerElementsAndRealizeNextAndHasNextThenNewDynamicArrayList() {
        DynamicArrayList<Integer> list = new DynamicArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(list.get(0)));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(list.get(1)));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(list.get(2)));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(list.get(3)));
    }


}
