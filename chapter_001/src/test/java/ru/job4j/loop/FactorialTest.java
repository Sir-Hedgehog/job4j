package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class FactorialTest {
    @Test
    public void whenNumberMoreOne() {
        Factorial fact = new Factorial();
        int result = fact.calc(4);
        assertThat(result, is(24));
    }

    @Test
    public void whenNumberIsZero() {
        Factorial fact = new Factorial();
        int result = fact.calc(0);
        assertThat(result, is(1));
    }
}
