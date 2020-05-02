package ru.job4j.storage;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 12.01.2020
 */

public class StorageTest {
    private User user1 = new User(1, 500);
    private User user2 = new User(2, 1000);
    private User user3 = new User(3, 2000);
    private User user4 = new User(4, 3500);
    private User user5 = new User(5, 5000);

    /**
     * Класс описывает нить с переводом денег между пользователями.
     */

    private class ThreadStorage extends Thread {
        private final UserStorage userStorage;

        private ThreadStorage(final UserStorage userStorage) {
            this.userStorage = userStorage;
        }

        @Override
        public void run() {
            this.userStorage.transfer(user3.getId(), user1.getId(), 500);
            this.userStorage.transfer(user5.getId(), user3.getId(), 700);
            this.userStorage.transfer(user2.getId(), user4.getId(), 150);
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        //Создаем хранилище пользователей.
        final UserStorage userStorage = new UserStorage();
        userStorage.add(user1);
        userStorage.add(user2);
        userStorage.add(user3);
        userStorage.add(user4);
        userStorage.add(user5);
        //Создаем нити.
        Thread first = new ThreadStorage(userStorage);
        Thread second = new ThreadStorage(userStorage);
        Thread third = new ThreadStorage(userStorage);
        //Запускаем нити.
        first.start();
        second.start();
        third.start();
        //Заставляем главную нить дождаться выполнения наших нитей.
        first.join();
        second.join();
        third.join();
        //Обновляем данные пользователя.
        userStorage.update(user4, 10, 3750);
        //Удаляем пользователя.
        userStorage.delete(user5);
        //Предлагаем jvm прервать нити
        first.interrupt();
        second.interrupt();
        third.interrupt();
        //Проверяем результат.
        assertThat(user1.getAmount(), is(2000));
        assertThat(user2.getAmount(), is(550));
        assertThat(user3.getAmount(), is(2600));
        assertThat(user4.getAmount(), is(3750));
        assertThat(user4.getId(), is(10));
    }
}
