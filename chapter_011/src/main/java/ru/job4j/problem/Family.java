package ru.job4j.problem;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 10.01.2020
 */

public class Family implements Runnable {
    private Bank bank;
    private int sum;
    private String name;

    public Family(Bank bank) {
        this.bank = bank;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод снимает деньги со семейного счета в банке.
     */

    @Override
    public void run() {
        while (bank.getScore() - sum >= 0) {
            System.out.println(this.name + " снимает деньги!");
            bank.setScore(bank.getScore() - sum);
            System.out.println("Остаток на банковском счете: " + bank.getScore());
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (bank.getScore() < 0) {
            System.out.println("Превышение лимита!");
        }
    }
}
