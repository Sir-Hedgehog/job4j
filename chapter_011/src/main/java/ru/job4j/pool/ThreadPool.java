package ru.job4j.pool;

import ru.job4j.blocking.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 22.01.2020
 */

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(3);
    private final int size = Runtime.getRuntime().availableProcessors();

    /**
     * Метод осуществляет работу по заполнению очереди и запуску потоков из пула
     * @param job - процесс работы
     */

    public void work(Runnable job) throws InterruptedException  {
        while (threads.size() != size) {
            Thread thread = new Thread(new ThreadManager());
            threads.add(thread);
            tasks.offer(job);
            thread.start();
            thread.join();
        }
    }

    /**
     * Метод закрывает потоки в случае прекращения работы
     */

    public void shutdown() {
        for (Thread thread : threads) {
            if (!thread.isAlive()) {
                thread.interrupt();
            }
        }
    }

    /**
     * Метод проверяет, все ли потоки закрыты
     */

    public boolean isStopped() {
        boolean result = false;
        for (Thread thread : threads) {
            if (thread.isAlive()) {
                result = false;
                break;
            } else {
                result = true;
            }
        }
        return result;
    }

    /**
     * Класс, реализующий принцип работы потока
     */

    private class ThreadManager extends Thread {
        @Override
        public void run() {
            tasks.poll();
        }
    }
}
