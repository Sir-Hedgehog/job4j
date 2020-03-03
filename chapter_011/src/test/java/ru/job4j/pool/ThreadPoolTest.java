package ru.job4j.pool;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 5.0
 * @since 03.03.2020
 */

public class ThreadPoolTest {
    @Test
    public void checkWorkOfThreadPoolForHundredElements() {
        ThreadPool pool = new ThreadPool();
        int expected = 100;
        for (int index = 0; index < 100; index++) {
            pool.work(() -> System.out.println("Just do it!"));
        }
        pool.shutdown();
        assertThat(expected, is(pool.getCount().intValue()));
    }
}
