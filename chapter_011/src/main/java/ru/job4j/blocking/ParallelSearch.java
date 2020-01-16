package ru.job4j.blocking;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 16.01.2020
 */

public class ParallelSearch {
    public static void main(String[] args) {
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(4);
        final Thread producer = new Thread(
            () -> {
                for (int index = 0; index != 5; index++) {
                    queue.offer(index);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        );
        producer.start();
        final Thread consumer = new Thread(
            () -> {
                while (producer.isAlive()) {
                    System.out.println(queue.poll());
                }
            }
        );
        consumer.start();
    }
}
