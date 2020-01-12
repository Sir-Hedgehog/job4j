package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 12.01.2020
 */

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private List<User> storage = new ArrayList<>();

    /**
     * Метод добавляет пользователя в хранилище
     * @param user - пользователь
     */

    public synchronized void add(User user) {
        storage.add(user);
    }

    /**
     * Метод обновляет данные пользователя в хранилище
     * @param user - пользователь
     * @param id - новый идентификатор пользователя
     * @param amount - новое состояние счета пользователя
     * @return - успешность операции
     */

    public synchronized boolean update(User user, int id, int amount) {
        boolean result = false;
        if (storage.contains(user)) {
            if (id != user.getId() || amount != user.getAmount()) {
                user.setId(id);
                user.setAmount(amount);
                result = true;
            }
        } else {
            throw new NullPointerException("Пользователь не найден!");
        }
        return result;
    }

    /**
     * Метод удаляет пользователя из хранилища
     * @param user - пользователь
     */

    public synchronized void delete(User user) {
        storage.remove(user);
    }

    /**
     * Метод переводит деньги из одного счета на другой
     * @param fromId - идентификатор отправителя
     * @param toId - идентификатор получателя
     * @param amount - сумма перевода
     */

    public synchronized void transfer(int fromId, int toId, int amount) {
        boolean current1;
        boolean current2;
        User first = null;
        User second = null;
        for (User client : storage) {
            if (client.getId() == fromId) {
                first = client;
                break;
            }
        }
        for (User client : storage) {
            if (client.getId() == toId) {
                second = client;
                break;
            }
        }
        if (first != null && second != null) {
            current1 = this.update(first, first.getId(), first.getAmount() - amount);
            current2 = this.update(second, second.getId(), second.getAmount() + amount);
            if (!current1 || !current2) {
                try {
                    throw new Exception("Ошибка обновления данных!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new NullPointerException("Пользователь не найден!");
        }
    }
}
