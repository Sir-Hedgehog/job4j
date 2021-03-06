package ru.job4j.blocking;

import org.junit.Test;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.0
 * @since 17.01.2020
 */

public class BlockingQueueTest {
    private class ProducerThread extends Thread {
        private final Producer<Object> producer;

        private ProducerThread(final Producer<Object> producer) {
            this.producer = producer;
        }

        @Override
        public void run() {
            this.producer.offer(1);
            this.producer.offer(2);
            this.producer.offer(3);
            this.producer.offer(4);
        }
    }

    private class ConsumerThread extends Thread {
        private final Consumer<Object> consumer;

        private ConsumerThread(final Consumer<Object> consumer) {
            this.consumer = consumer;
        }

        @Override
        public void run() {
            this.consumer.poll();
            this.consumer.poll();
            this.consumer.poll();
        }
    }

    @Test
    public void checkSizeOfQueueDuringTheWorkingOfProducerAndConsumer() throws InterruptedException {
        final SimpleBlockingQueue<Object> queue = new SimpleBlockingQueue<>(3);
        Producer<Object> producer = new Producer<>(queue);
        Consumer<Object> consumer = new Consumer<>(queue);
        ProducerThread producerThread = new ProducerThread(producer);
        ConsumerThread consumerThread = new ConsumerThread(consumer);
        Thread first = new Thread(producerThread);
        Thread second = new Thread(consumerThread);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(consumer.poll(), is(4));
        assertThat(queue.getFactSize(), is(0));
        producer.offer(5);
        assertThat(consumer.poll(), is(5));
        first.interrupt();
        second.interrupt();
    }

    @Test
    public void whenFillConsistentlyQueueThenInterruptConsumer() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread producer = new Thread(
                () -> IntStream.range(0, 5).forEach(
                        queue::offer
                )
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (queue.getFactSize() != 0 || !Thread.currentThread().isInterrupted()) {
                        buffer.add(queue.poll());
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
        producer.interrupt();
    }

    @Test
    public void whenEmptyConsistentlyQueueThenInterruptProducer() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        for (int index = 0; index < 5; index++) {
            queue.offer(index);
        }
        Thread consumer = new Thread(
                () -> {
                    for (int index = 0; index < 5; index++) {
                        queue.poll();
                    }
                }
        );
        consumer.start();
        Thread producer = new Thread(
                () -> {
                    while (queue.getFactSize() != queue.getNominalSize() || !Thread.currentThread().isInterrupted()) {
                        for (int index = 0; index < 5; index++) {
                            queue.offer(index);
                            buffer.add(index);
                        }
                    }
                }
        );
        producer.start();
        consumer.join();
        producer.interrupt();
        producer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
        consumer.interrupt();
    }
}
