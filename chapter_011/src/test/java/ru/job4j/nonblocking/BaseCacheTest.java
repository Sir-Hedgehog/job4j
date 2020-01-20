package ru.job4j.nonblocking;

import org.junit.Assert;
import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 20.01.2020
 */

public class BaseCacheTest {
    private Base base1 = new Base(1);
    private Base base2 = new Base(2);
    private AtomicReference<Exception> ex = new AtomicReference<>();

    private class ThreadStorage extends Thread {

        private final BaseCache cache;

        private ThreadStorage(final BaseCache cache) {
            this.cache = cache;
        }

        @Override
        public void run() {
            try {
                cache.add(base1);
                cache.add(base2);
                cache.update(base1);
                cache.delete(base2);
            } catch (OptimisticException oe) {
                ex.set(oe);
            }
        }
    }

    @Test
    public void whenThrowException() throws InterruptedException {
        BaseCache cache = new BaseCache();
        ThreadStorage thread1 = new ThreadStorage(cache);
        ThreadStorage thread2 = new ThreadStorage(cache);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(base1.getVersion().intValue(), is(2));
        //Assert.assertThat(ex.get().getMessage(), is("Ошибка при обновлении данных в потоке!"));
    }
}

