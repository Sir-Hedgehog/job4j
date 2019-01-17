package ru.job4j.bank;

import java.util.TreeMap;
import java.util.ArrayList;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 17.01.2019
 */

public class Bank {

    TreeMap<User, ArrayList<Account>> map = new TreeMap<>();

    /**
     * Метод реализует добавление нового клиента с его списком счетов
     * @param user - клиент
     */

    public void addUser(User user) {
        ArrayList<Account> accounts = new ArrayList<>();
        this.map.put(user, accounts);
    }

    /**
     * Метод удаляет нового клиента с его списком счетов
     * @param user - клиент
     */

    public void deleteUser(User user) {
        if (user != null) {
            this.map.remove(user);
        }
    }

    /**
     * Метод выдает список всех клиентов
     * @return users - клиенты
     */

    public ArrayList<User> getAllUsers() {
        return new ArrayList<>(this.map.keySet());
    }

    /**
     * Метод добавляет клиенту новый счет
     * @param user - клиент
     * @param account - счет
     */

    public void addAccountToUser(User user, Account account) {
        this.map.get(user).add(account);
    }

    /**
     * Метод удаляет клиенту счет
     * @param user - клиент
     * @param account - счет
     */

    public void deleteAccountFromUser(User user, Account account) {
        this.map.get(user).remove(account);
    }

    /**
     * Метод выдает список счетов пользователя
     * @param user - клиент
     * @return список счетов
     */

    public ArrayList<Account> getUserAccounts(User user) {
        return this.map.get(user);
    }

    /**
     * Метод выдает запрашиваемый счет
     * @param user - клиент
     * @param account - счет
     * @return запрашиваемый счет
     */

    public Account getActualAccount(User user, Account account) {
        ArrayList<Account> list = this.map.get(user);
        return list.get(list.indexOf(account));
    }

    /**
     * Метод для перечисления денег с одного счета на другой
     * @param user1 - первый клиент
     * @param srcAccount - счет первого клиента
     * @param user2 - второй клиент
     * @param destAccount - счет второго клиента
     * @param amount - количество денег
     */

    public boolean transferMoney (User user1, Account srcAccount, User user2, Account destAccount, double amount) {
        return this.map.get(user1).contains(srcAccount) &&
                this.map.get(user2).contains(destAccount) &&
                getActualAccount(user1, srcAccount).transfer(getActualAccount(user2, destAccount), amount);
    }
}
