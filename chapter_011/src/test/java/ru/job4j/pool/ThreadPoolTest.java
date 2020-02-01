package ru.job4j.pool;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 01.02.2020
 */

public class ThreadPoolTest {
    @Test
    public void checkWorkOfThreadPool() {
        ThreadPool pool = new ThreadPool();
        for (int index = 0; index < 100; index++) {
            pool.work(() -> System.out.println("Пул нитей закончил процесс обработки задачи..."));
        }
        pool.shutdown();
        assertThat(pool.isStopped(), is(true));
    }
}
