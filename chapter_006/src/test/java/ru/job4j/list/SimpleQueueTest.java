package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    @Test
    public void whenAddManyIntegerElementsAndRealizeNextAndHasNextThenNewDynamicArrayList() {
        SimpleQueue<Integer> list = new SimpleQueue<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        assertThat(list.poll(), is(1));
        assertThat(list.poll(), is(2));
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
    }
}
