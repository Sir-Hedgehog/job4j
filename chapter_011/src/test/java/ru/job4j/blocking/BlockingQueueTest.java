package ru.job4j.blocking;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 16.01.2020
 */

public class BlockingQueueTest {
    private class ProducerThread extends Thread {
        private final Producer producer;

        private ProducerThread(final Producer producer) {
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
        private final Consumer consumer;

        private ConsumerThread(final Consumer consumer) {
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
    public void checkGetSize() throws InterruptedException {
        final SimpleBlockingQueue queue = new SimpleBlockingQueue<>(3);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        ProducerThread producerThread = new ProducerThread(producer);
        ConsumerThread consumerThread = new ConsumerThread(consumer);
        Thread first = new Thread(producerThread);
        Thread second = new Thread(consumerThread);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(consumer.poll(), is(4));
        assertThat(queue.getSize(), is(0));
        producer.offer(5);
        assertThat(consumer.poll(), is(5));
    }
}
