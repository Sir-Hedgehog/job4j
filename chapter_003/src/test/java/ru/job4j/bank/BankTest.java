package ru.job4j.bank;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 19.01.2019
 */

public class BankTest {

    @Test
    public void whenAddUserAndYourAccountsThenReturnSizeOfTheseAccounts() {
        User user = new User("Ivanova Natalya", "4503675810");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccountToUser("4503675810", new Account(1000, "RK278U"));
        bank.addAccountToUser("4503675810", new Account(15004, "TY567V"));
        assertThat(bank.getAllUsers().get(0), is(user));
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
        bank.addAccountToUser("4503675810", new Account(1000, "UY278I"));
        bank.addAccountToUser("4512012589", new Account(15004, "TR567U"));
        bank.addAccountToUser("4802159357", new Account(1500434, "GF190U"));
        bank.deleteUser(user1);
        assertThat(bank.getAllUsers().size(), is(2));
    }

    @Test
    public void whenDeleteUserAccountsThenReturnListOfOtherUserAccounts() {
        User user = new User("Ivanova Natalya", "4503675810");
        Account account1 = new Account(1000, "UY278I");
        Account account2 = new Account(10600, "MJ256I");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccountToUser("4503675810", account1);
        bank.addAccountToUser("4503675810", account2);
        bank.deleteAccountFromUser("4503675810", account1);
        assertThat(bank.getUserAccounts("4503675810").size(), is(1));
    }

    @Test
    public void whenTransferMoneyThenReturnUpdateData() {
        User sender = new User("Ivanova Natalya", "4503675810");
        Account account1 = new Account(1500, "RU278G");
        User addressee = new User("Petrov Sidor", "3820598123");
        Account account2 = new Account(7000, "RT678E");
        Bank bank = new Bank();
        bank.addUser(sender);
        bank.addUser(addressee);
        bank.addAccountToUser("4503675810", account1);
        bank.addAccountToUser("3820598123", account2);
        bank.transferMoney("4503675810", "RU278G", "3820598123", "RT678E", 500);
        assertThat(bank.getUserAccounts("3820598123").get(0).getValue(), is(7500.0));
    }

}
