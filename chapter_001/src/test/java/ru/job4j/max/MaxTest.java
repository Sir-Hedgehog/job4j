package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class MaxTest {
    @Test
    public void whenThirdMoreSecondAndFirst() {
        Max maxim = new Max();
        int result = maxim.max(1, 2, 3);
        assertThat(result, is(3));
    }

    @Test
    public void whenThirdMoreFirstAndSecond() {
        Max maxim = new Max();
        int result = maxim.max(2, 1, 3);
        assertThat(result, is(3));
    }

    @Test
    public void whenFirstMoreSecondAndThird() {
        Max maxim = new Max();
        int result = maxim.max(3, 2, 1);
        assertThat(result, is(3));
    }

    @Test
    public void whenFirstMoreThirdAndSecond() {
        Max maxim = new Max();
        int result = maxim.max(3, 1, 2);
        assertThat(result, is(3));
    }

    @Test
    public void whenSecondMoreFirstAndThird() {
        Max maxim = new Max();
        int result = maxim.max(2, 3, 1);
        assertThat(result, is(3));
    }

    @Test
    public void whenSecondMoreThirdAndFirst() {
        Max maxim = new Max();
        int result = maxim.max(1, 3, 2);
        assertThat(result, is(3));
    }
}
