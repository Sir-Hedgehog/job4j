package ru.job4j.list;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    @Test
    public void whenAddIntegerElementsAndRealizeNextAndHasNextThenNewQueue() {
        SimpleStack<Integer> one = new SimpleStack<>();
        SimpleStack<Integer> two = new SimpleStack<>();
        SimpleQueue<Integer> queue = new SimpleQueue<>(one, two);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        Iterator<Integer> it = two.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
    }
}
