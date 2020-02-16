package ru.job4j.pool;

import ru.job4j.blocking.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.0
 * @since 16.02.2020
 */

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(8);
    private volatile boolean isRunning = true;

    /**
     * В конструкторе создается пул потоков, размер которого равен количеству потоков процессора.
     */

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int index = 0; index < size; index++) {
            Thread thread = new Thread(new ThreadManager());
            threads.add(thread);
            thread.start();
        }
    }

    /**
     * Метод осуществляет работу по заполнению очереди
     * @param job - задание
     */

    public void work(Runnable job) {
        if (isRunning) {
            tasks.offer(job);
        }
    }

    /**
     * Метод прерывает потоки в случае прекращения работы
     */

    public void shutdown() {
        isRunning = false;
        for (Thread thread : threads) {
            thread.interrupt();
            if (thread.isInterrupted()) {
                System.out.println("Поток " + thread.getName() + " успешно выполнил свою миссию и находится на последних стадиях самоликвидации");
            }
            if (!thread.isAlive()) {
                System.out.println("Поток " + thread.getName() + " успешно выполнил свою миссию и самоликвидировался");
            }
        }
    }

    /**
     * Метод проверяет, все ли потоки прерваны
     */

    public boolean isStopped() {
        boolean result = false;
        for (Thread thread : threads) {
            if (!thread.isAlive() || thread.isInterrupted()) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Класс, реализующий принцип работы потока
     */

    private final class ThreadManager extends Thread {
        @Override
        public void run() {
            while (isRunning) {
                Runnable nextTask = tasks.poll();
                if (!Thread.currentThread().isInterrupted()) {
                    nextTask.run();
                }
                Thread.currentThread().interrupt();
            }
        }
    }
}
