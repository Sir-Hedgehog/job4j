package ru.job4j.crud;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 18.02.2020
 */

public class MemoryStore implements Store {
    private CopyOnWriteArrayList<User> users;

    private static MemoryStore memoryInstance = new MemoryStore();

    public MemoryStore() {
        this.users = new CopyOnWriteArrayList<>();
    }

    /**
     * Метод дает право создать единственный экзепляр класса для взаимосвязи с логическим (валидационным) блоком проекта
     * @return - экзепляр класса MemoryStore
     */

    public static MemoryStore getInstance() {
        return memoryInstance;
    }

    /**
     * Метод добавляет пользователя
     * @param user - пользователь
     * @return - успешность операции
     */

    @Override
    public boolean add(User user) {
        return this.users.add(user);
    }

    /**
     * Метод обновляет данные существующего пользователя
     * @param id - идентификатор существующего пользователя
     * @param recent - новые данные о пользователе
     * @return - успешность операции
     */

    @Override
    public boolean update(int id, User recent) {
        boolean result = false;
        for (User old : users) {
            if (id == old.getId()) {
                old.setName(recent.getName());
                old.setLogin(recent.getLogin());
                old.setEmail(recent.getEmail());
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод удаляет существующего пользователя
     * @param id - идентификатор существующего пользователя
     * @return - успешность операции
     */

    @Override
    public boolean delete(int id) {
        boolean result = false;
        for (User user : users) {
            if (id == user.getId()) {
                this.users.remove(user);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод получает список существующих пользователей
     * @return - список существующих пользователей
     */

    @Override
    public CopyOnWriteArrayList<User> findAll() {
        return this.users;
    }

    /**
     * Метод получает данные существующего пользователя по id
     * @return - данные существующего пользователя
     */

    @Override
    public User findById(int id) {
        User result = null;
        for (User user : users) {
            if (id == user.getId()) {
                result = user;
            }
        }
        return result;
    }
}
