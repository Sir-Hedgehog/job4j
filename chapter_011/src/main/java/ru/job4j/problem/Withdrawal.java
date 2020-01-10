package ru.job4j.problem;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 10.01.2020
 */

public class Withdrawal {
    public static void main(String[] args) {
        Bank account = new Bank();
        account.setScore(10000);
        Family mother = new Family(account);
        mother.setName("Maма");
        mother.setSum(300);
        Family father = new Family(account);
        father.setName("Отец");
        father.setSum(700);
        Family son = new Family(account);
        son.setName("Сын");
        son.setSum(500);
        new Thread(father).start();
        new Thread(mother).start();
        new Thread(son).start();
    }
}
