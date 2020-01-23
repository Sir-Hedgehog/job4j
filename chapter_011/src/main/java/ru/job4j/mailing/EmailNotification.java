package ru.job4j.mailing;

import java.util.concurrent.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 23.01.2020
 */

public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Метод создате шаблон рассылки для существующих пользователей
     * @param user - пользователь
     */

    public void emailTo(User user) {
        String name = "Notification " + user.getName();
        String email = " to email " + user.getEmail();
        String body = "\nAdd a new event to " + user.getName() + "\n";
        this.send(name, body, email);
    }

    /**
     * Метод закрывает пул потоков
     */

    public boolean close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Закрытие потока " + Thread.currentThread().getName());
        return pool.isTerminated();
    }

    /**
     * Метод при помощи пула потоков отправляет рассылку с учетом заданного шаблона
     * @param subject - тема рассылки
     * @param body - тело рассылки
     * @param email - почта получателя
     */

    private void send(String subject, String body, String email) {
        pool.submit(() -> subject);
        pool.submit(() -> email);
        pool.submit(() -> body);
        System.out.println(subject + email + body);
    }
}
