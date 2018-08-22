package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {
    @Test
    public void whenNumberMoreZero() {
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
