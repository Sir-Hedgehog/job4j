package ru.job4j.set;

import org.junit.Test;
import ru.job4j.list.SimpleDynamicArray;
import java.util.Iterator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest {
    @Test
    public void whenAddManyIntegerElementsAndRealizeNextAndHasNextThenNewDynamicArray() {
        SimpleDynamicArray<Integer> array = new SimpleDynamicArray<>();
        SimpleSet<Integer> set = new SimpleSet<>(array);
        set.add(10);
        set.add(15);
        set.add(7);
        set.add(7);
        set.add(4);
        set.add(7);
        set.add(10);
        Iterator<Integer> it = set.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(10));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(15));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));

    }
}
