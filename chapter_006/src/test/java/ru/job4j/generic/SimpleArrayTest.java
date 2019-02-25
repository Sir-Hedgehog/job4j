package ru.job4j.generic;

import java.util.Iterator;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {
    @Test
    public void whenRemoveIntegerElementsAndRealizeNextAndHasNextThenNewArray() {
        SimpleArray<Integer> simple = new SimpleArray<>(4);
        simple.add(10);
        simple.add(15);
        simple.add(4);
        simple.add(7);
        Iterator<Integer> it = simple.iterator();
        assertThat(simple.remove(1), is(true));
        assertThat(simple.remove(3), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(simple.get(0)));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(simple.get(1)));
    }

    @Test
    public void whenSetStringElementAndRealizeOnlyNextThenNewArray() {
        SimpleArray<String> simple = new SimpleArray<>(4);
        simple.add("Galaxy");
        simple.add("MiBand");
        simple.add("Note");
        simple.add("8S");
        Iterator<String> it = simple.iterator();
        assertThat(simple.set(1, "Danger"), is(true));
        assertThat(it.next(), is(simple.get(0)));
        assertThat(it.next(), is("Danger"));
        assertThat(it.next(), is(simple.get(2)));
        assertThat(it.next(), is(simple.get(3)));
    }

    @Test
    public void whenSetStringElementAndRealizeNextAndHasNextThenNewArray() {
        SimpleArray<Character> simple = new SimpleArray<>(3);
        simple.add('C');
        simple.add('S');
        simple.add('U');
        Iterator<Character> it = simple.iterator();
        assertThat(simple.set(2, 'E'), is(true));
        assertThat(it.next(), is(simple.get(0)));
        assertThat(it.next(), is(simple.get(1)));
        assertThat(it.next(), is('E'));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenOverflowSizeThenGetException() {
        SimpleArray<Double> simple = new SimpleArray<>(2);
        simple.add(10.4);
        simple.add(4.9);
        simple.add(5.3);
    }
}
