package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class CounterTest {
    @Test
    public void sumOfEvenNumbers() {
        Counter counter = new Counter();
        int result = counter.add(1, 8);
        assertThat(result, is(20));
    }
}
