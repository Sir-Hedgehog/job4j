package ru.job4j.bank;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 17.01.2019
 */

public class BankTest {

    @Test
    public void whenAddUserAndYourAccountsThenReturnSizeOfTheseAccounts() {
        User user = new User("Ivanova Natalya", "4503675810");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccountToUser(user, new Account(1000, "RK278Z"));
        bank.addAccountToUser(user, new Account(15004, "RZ567G"));
        assertThat(bank.getUserAccounts(user).size(), is(2));
    }

    @Test
    public void whenDeleteUserThenReturnListOfOtherUsers() {
        User user1 = new User("Petrova Natalya", "4503675810");
        User user2 = new User("Gromov Vitaliy", "4512012589");
        User user3 = new User("Drozd Fedor", "4802159357");
        Bank bank = new Bank();
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addUser(user3);
        bank.addAccountToUser(user1, new Account(1000, "RK278Z"));
        bank.addAccountToUser(user2, new Account(15004, "RZ567G"));
        bank.addAccountToUser(user3, new Account(1500434, "RM190U"));
        bank.deleteUser(user1);
        assertThat(bank.getAllUsers().size(), is(2));
    }

    @Test
    public void whenDeleteUserAccountsThenReturnListOfOtherUserAccounts() {
        User user = new User("Ivanova Natalya", "4503675810");
        Account account1 = new Account(1000, "RK278Z");
        Account account2 = new Account(10600, "KL256Y");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccountToUser(user, account1);
        bank.addAccountToUser(user, account2);
        bank.deleteAccountFromUser(user, account1);
        assertThat(bank.getUserAccounts(user).size(), is(1));
    }

    @Test
    public void whenTransferMoneyThenReturnUpdateData() {
        User sender = new User("Ivanova Natalya", "4503675810");
        Account account1 = new Account(1500, "RK278Z");
        User addressee = new User("Ivanova Natalya", "4503675810");
        Account account2 = new Account(7000, "RU678Z");
        Bank bank = new Bank();
        bank.addUser(sender);
        bank.addUser(addressee);
        bank.addAccountToUser(sender, account1);
        bank.addAccountToUser(addressee, account2);
        bank.transferMoney(sender, account1, addressee, account2, 500);
        assertThat(bank.getUserAccounts(addressee).get(1).getValue(), is(7500.0));
    }

}
