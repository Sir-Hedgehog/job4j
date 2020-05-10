package ru.job4j.nonblocking;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 10.05.2020
 */

public class CASQueueTest {
    @Test
    public void when1PushThen1Poll() {
        CASQueueInterface<Integer> queue = new CASQueue<>();
        queue.push(1);
        assertThat(queue.poll(), is(1));
    }

    @Test
    public void when2PushThen2Poll() {
        CASQueueInterface<Integer> queue = new CASQueue<>();
        queue.push(1);
        queue.push(2);
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
    }

    @Test
    public void when3PushThen3Poll() {
        CASQueueInterface<Integer> queue = new CASQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
    }
}
