package ru.job4j.nonblocking;

import org.junit.Assert;
import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 03.02.2020
 */

public class BaseCacheTest {
    private Base base;
    private BaseCache cache = new BaseCache();
    private AtomicReference<Exception> ex = new AtomicReference<>();
    private Thread thread1 = new Thread(() -> {
        try {
            base = new Base(1, "Иван");
            cache.update(base);
        } catch (OptimisticException e) {
            ex.set(e);
        }
    });
    private Thread thread2 = new Thread(() -> {
        try {
            base = new Base(1, "Василий");
            cache.update(base);
        } catch (OptimisticException e) {
            ex.set(e);
        }
    });

    @Test
    public void whenOneThreadTryUpdateInfoAndVersionsAreEqualThenTrue() throws InterruptedException {
        base = new Base(1, "Александр");
        cache.add(base);
        thread1.start();
        thread1.join();
        assertThat(base.getVersion(), is(1));
    }

    @Test
    public void whenTwoThreadsTryUpdateInfoAndVersionsAreNotEqualThenThrowException() throws InterruptedException {
        base = new Base(1, "Александр");
        cache.add(base);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Assert.assertThat(ex.get().getMessage(), is("Ошибка при обновлении данных в потоке!"));
    }
}

