package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest<T> {
    @Test
    public void whenAddManyIntegerElementsAndRealizeNextAndHasNextThenNewDynamicArrayList() {
        SimpleStack<Integer> list = new SimpleStack<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        assertThat(list.poll(), is(4));
        assertThat(list.poll(), is(3));
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
    }
}
