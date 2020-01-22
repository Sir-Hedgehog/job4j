package ru.job4j.pool;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 22.01.2020
 */

public class ThreadPoolTest {
    @Test
    public void checkWorkOfThreadPool() throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        Runnable job = null;
        for (int index = 0; index < 2_000_000_000; index++) {
            job = System.out::println;
        }
        pool.work(job);
        pool.shutdown();
        assertThat(pool.isStopped(), is(true));
    }
}
