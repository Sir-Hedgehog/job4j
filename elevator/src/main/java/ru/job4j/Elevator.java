package ru.job4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 19.03.2020
 */

public class Elevator implements ElevatorInterface {
    private final int floors;
    private int limit;
    private BlockingQueue<Human> elevator;
    private ExecutorService pool;
    private AtomicInteger currentFloor = new AtomicInteger(1);

    public Elevator(int floors, int limit) {
        this.limit = limit;
        this.floors = floors;
        elevator = new ArrayBlockingQueue<>(limit);
        pool = Executors.newFixedThreadPool(limit);
    }

    /**
     * Метод задает движение лифта по этажам и формирует работу по открытию и закрытию дверей
     */

    public void move() throws ExecutionException, InterruptedException {
        int range = 0;
        while (!this.pool.isTerminated()) {
            range = ThreadLocalRandom.current().nextInt(-9, 9);
            if (currentFloor.get() + range > 0 && currentFloor.get() + range <= floors && range != 0) {
                currentFloor.set(currentFloor.get() + range);
                break;
            }
        }
        if (range < 0) {
            System.out.println(String.format("Лифт опускается на %d этаж/этажа/этажей", range * (-1)));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.goOut();
            this.comeIn();
            this.closeDoors();
        } else {
            System.out.println(String.format("Лифт поднимается на %d этаж/этажа/этажей", range));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.goOut();
            this.comeIn();
            this.closeDoors();
        }
    }

    /**
     * Метод детализирует работу потоков, имитирующих процесс заполнения лифта
     */

    @Override
    public void comeIn() throws ExecutionException, InterruptedException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Future entry = pool.submit(() -> new Entry().call());
        while (!entry.isDone()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("В лифт зашел/зашло %s человек/человека", entry.get().toString()));
    }

    /**
     * Метод детализирует работу потоков, имитирующих процесс высвобождения лифта
     */

    @Override
    public void goOut() throws ExecutionException, InterruptedException {
        System.out.println(String.format("Лифт остановился на %d этаже и открыл двери", currentFloor.get()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Future exit = pool.submit(() -> new Exit().call());
        while (!exit.isDone()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("Из лифта вышел/вышло %s человек/человека", exit.get().toString()));
    }

    /**
     * Метод закрывает двери и считывает фактическое количество человек в лифте
     */

    @Override
    public void closeDoors() {
        System.out.println("Двери закрыты. На данный момент количество людей в лифте: " + elevator.size());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод оканчивает работу лифта
     * @return - успешность окончания
     */

    @Override
    public boolean closePool() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Лифт сломался. Кто-нибудь, вызовите диспетчера!");
        return pool.isTerminated();
    }

    /**
     * Задание для потоков по высвобождению лифта
     */

    private class Exit implements Callable {
        @Override
        public Integer call() {
            AtomicInteger index = new AtomicInteger(0);
            if (!elevator.isEmpty()) {
                while (index.get() <= elevator.size()) {
                    try {
                        elevator.take();
                        index.incrementAndGet();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return index.intValue();
        }
    }

    /**
     * Задание для потоков по заполнению лифта
     */

    private class Entry implements Callable {
        @Override
        public Integer call() {
            AtomicInteger index = new AtomicInteger(0);
            AtomicInteger fullness = new AtomicInteger(0);
            fullness.set(ThreadLocalRandom.current().nextInt(1, limit));
            while (fullness.intValue() + elevator.size() > limit) {
                fullness.set(ThreadLocalRandom.current().nextInt(1, limit));
            }
            while (index.get() < fullness.intValue()) {
                elevator.add(new Human());
                index.incrementAndGet();
            }
            return index.get();
        }
    }
}
