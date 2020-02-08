package ru.job4j.pool;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 08.02.2020
 */

public class ThreadPoolTest {
    @Test
    public void checkWorkOfThreadPoolForOneElement() {
        ThreadPool pool = new ThreadPool();
        for (int index = 0; index < 1; index++) {
            pool.work(() -> System.out.println("Just do it!"));
        }
        pool.shutdown();
        assertThat(pool.isStopped(), is(true));
    }

    @Test
    public void checkWorkOfThreadPoolForTenElements() {
        ThreadPool pool = new ThreadPool();
        for (int index = 0; index < 10; index++) {
            pool.work(() -> System.out.println("Just do it!"));
        }
        pool.shutdown();
        assertThat(pool.isStopped(), is(true));
    }

    @Test
    public void checkWorkOfThreadPoolForHundredElements() {
        ThreadPool pool = new ThreadPool();
        for (int index = 0; index < 100; index++) {
            pool.work(() -> System.out.println("Just do it!"));
        }
        pool.shutdown();
        assertThat(pool.isStopped(), is(true));
    }
}
