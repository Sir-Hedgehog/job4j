package ru.job4j.bank;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 22.01.2019
 */

public class Bank {

    private Map<User, ArrayList<Account>> map = new HashMap<>();

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
     * @param passport - паспотрные данные клиента
     * @param account - счет
     */

    public void addAccountToUser(String passport, Account account) {
        for (User user : this.map.keySet()) {
            if (user.getPassport().equals(passport)) {
                this.map.get(user).add(account);
            }
            break;
        }
    }

    /**
     * Метод удаляет клиенту счет
     * @param passport - паспортные данные клиента
     * @param account - счет
     */

    public void deleteAccountFromUser(String passport, Account account) {
        for (User user : this.map.keySet()) {
            if (user.getPassport().equals(passport)) {
                this.map.get(user).remove(account);
            }
            break;
        }
    }

    /**
     * Метод выдает список счетов пользователя
     * @param passport - паспортные данные клиента
     * @return список счетов
     */

    public ArrayList<Account> getUserAccounts(String passport) {
        ArrayList<Account> accounts = new ArrayList<>();
        for (User user : this.map.keySet()) {
            if (user.getPassport().equals(passport)) {
                accounts = this.map.get(user);
            }
            break;
        }
        return accounts;
    }

    /**
     * Метод выдает запрашиваемый счет
     * @param passport - паспортные данные клиента
     * @param requisites - реквизиты
     * @return запрашиваемый счет
     */

    public Account getActualAccount(String passport, String requisites) {
        Account account = null;
        final ArrayList<Account> userAccounts = getUserAccounts(passport);
        if (!userAccounts.isEmpty()) {
            account = userAccounts.stream()
                    .filter(acc -> acc.getRequisites()
                            .equals(requisites)).findFirst()
                    .orElse(null);
        }
        return account;
    }

    /**
     * Метод для перечисления денег с одного счета на другой
     * @param srcPassport - первый клиент
     * @param srcRequisite - счет первого клиента
     * @param descPassport - второй клиент
     * @param descRequisite - счет второго клиента
     * @param amount - количество денег
     */

    public boolean transferMoney (String srcPassport, String srcRequisite, String descPassport, String descRequisite, double amount) {
        boolean result = false;
        Account srcAccount = getActualAccount(srcPassport, srcRequisite);
        Account descAccount = getActualAccount(descPassport, descRequisite);
        if (srcAccount != null && descAccount != null) {
            if (amount > 0 && amount < srcAccount.getValue() && srcAccount.getValue() != 0) {
                srcAccount.minus(amount);
                descAccount.plus(amount);
                result = true;
            }
        }
        return result;
    }
}
