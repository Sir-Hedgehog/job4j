package ru.job4j.mailing;

import java.util.concurrent.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 09.02.2020
 */

public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Метод отправляет данные о пользователе
     * @param user - пользователь
     */

    public void emailTo(User user) {
        String subject = user.getName();
        String email = user.getEmail();
        String body = user.getName();
        this.send(subject, body, email);
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
     * @param subject - информация о пользователе для формирования темы рассылки
     * @param body - информация о пользователе для формирования тела рассылки
     * @param email - почта получателя
     */

    private void send(String subject, String body, String email) {
        Future task = pool.submit(() -> new Form(subject, body, email).call());
        while (!task.isDone()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println("New message:");
            System.out.println(task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * В классе Form происходит формирование шаблона рассылки
     */

    private class Form implements Callable<String> {
        String subject;
        String body;
        String email;

        Form(String subject, String body, String email) {
            this.subject = subject;
            this.body = body;
            this.email = email;
        }

        public String call() {
            return "Notification " + subject + " to email " + email + "\nAdd a new event to " + body + "\n";
        }
    }
}
