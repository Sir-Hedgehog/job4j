package ru.job4j.counter;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 11.01.2020
 */

public class CountTest {

    /**
     * Класс описывает нить со счетчиком.
     */

    private class ThreadCount extends Thread {
        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        //Создаем счетчик.
        final Count count = new Count();
        //Создаем нити.
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        Thread third = new ThreadCount(count);
        Thread fourth = new ThreadCount(count);
        Thread fifth = new ThreadCount(count);
        //Запускаем нити.
        first.start();
        second.start();
        third.start();
        fourth.start();
        fifth.start();
        //Заставляем главную нить дождаться выполнения наших нитей.
        first.join();
        second.join();
        third.join();
        fourth.join();
        fifth.join();
        //предлагаем jvm прервать нити
        first.interrupt();
        second.interrupt();
        third.interrupt();
        fourth.interrupt();
        fifth.interrupt();
        //Проверяем результат.
        assertThat(count.get(), is(5));
    }
}
