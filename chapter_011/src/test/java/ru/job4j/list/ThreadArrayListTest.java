package ru.job4j.list;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 13.01.2020
 */

public class ThreadArrayListTest {

    /**
     * Класс описывает нить с работой итератора.
     */

    private class ThreadStorage extends Thread {
        private final BasicArrayList list;

        private ThreadStorage(final BasicArrayList list) {
            this.list = list;
        }

        @Override
        public void run() {
            this.list.iterator();
        }
    }

    @Test
    public void whenExecute5ThreadThen5() throws InterruptedException {
        //Создаем список объектов типа E.
        //@SuppressWarnings("unchecked")
        final BasicArrayList<Integer> threadArrayList = new ThreadArrayList<>(new DynamicArrayList<>());
        threadArrayList.add(1);
        threadArrayList.add(2);
        threadArrayList.add(3);
        threadArrayList.add(4);
        threadArrayList.add(5);
        //Создаем нити.
        Thread first = new ThreadStorage(threadArrayList);
        Thread second = new ThreadStorage(threadArrayList);
        Thread third = new ThreadStorage(threadArrayList);
        Thread fourth = new ThreadStorage(threadArrayList);
        Thread fifth = new ThreadStorage(threadArrayList);
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
        //Предлагаем jvm прервать нити
        first.interrupt();
        second.interrupt();
        third.interrupt();
        fourth.interrupt();
        fifth.interrupt();
        //Проверяем результат.
        assertThat(1, is(threadArrayList.get(0)));
        assertThat(2, is(threadArrayList.get(1)));
        assertThat(3, is(threadArrayList.get(2)));
        assertThat(4, is(threadArrayList.get(3)));
        assertThat(5, is(threadArrayList.get(4)));
    }
}
