package ru.job4j.list;

import java.util.Iterator;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleDynamicArrayTest {
    @Test
    public void whenAddManyIntegerElementsAndRealizeNextAndHasNextThenNewDynamicArray() {
        SimpleDynamicArray<Integer> simple = new SimpleDynamicArray<>();
        simple.add(10);
        simple.add(15);
        simple.add(4);
        simple.add(7);
        Iterator<Integer> it = simple.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(simple.get(0)));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(simple.get(1)));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(simple.get(2)));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(simple.get(3)));

    }
}
