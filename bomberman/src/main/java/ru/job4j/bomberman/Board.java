package ru.job4j.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 16.02.2020
 */

public class Board {
    //Переменные для игрового поля
    private Cell[][] board;
    private final int limitX;
    private final int limitY;
    //Переменные для героя
    private int xHero = 0;
    private int yHero = 0;
    //Переменные для чудовищ
    private int numberOfMonsters;
    private List<List<Integer>> monsters = new ArrayList<>();
    private int xMonster;
    private int yMonster;
    //Переменные для блоков
    private int numberOfBlocks;
    private List<Cell> blocks = new ArrayList<>();
    //Переменная для запуска пула потоков
    private ExecutorService pool;

    /**
     * Исходя из входящих данных по размеру поля, конструктор формирует его поля:
     * количество блоков, количество чудовищ, инициализация ячеек, количество потоков
     * @param limitX - количество ячеек, отражающих размер поля по длине
     * @param limitY - количество ячеек, отражающих размер поля по ширине
     */

    public Board(int limitX, int limitY) {
        this.limitX = limitX;
        this.limitY = limitY;
        board = new Cell[limitX][limitY];
        for (int out = 0; out < board.length; out++) {
            for (int in = 0; in < board[0].length; in++) {
                board[out][in] = new Cell(out, in);
            }
        }
        numberOfMonsters = limitX * limitY / 12;
        numberOfBlocks = limitX * limitY / 4;
        pool = Executors.newFixedThreadPool(1 + numberOfMonsters);
    }

    /**
     * Метод осуществляет построение блоков на поле
     */

    private void buildBlocks()  {
        for (int index = 0; index < numberOfBlocks; index++) {
            int xBlock = (int) (Math.random() * limitX);
            int yBlock = (int) (Math.random() * limitY);
            while (xBlock == 0 && yBlock == 0 || blocks.contains(board[xBlock][yBlock])) {
                xBlock = (int) (Math.random() * limitX);
                yBlock = (int) (Math.random() * limitY);
            }
            if (board[xBlock][yBlock].locker.tryLock()) {
                blocks.add(board[xBlock][yBlock]);
            }
            System.out.println("Поставлен блок: (" + (xBlock + 1) + ", " + (yBlock + 1) + ")");
        }
        System.out.println();
    }

    /**
     * Метод формирует стартовую позицию героя
     */

    private void startHero() {
        board[xHero][yHero].locker.lock();
        System.out.println("Бомбермен стартует с позиции (" + (xHero + 1) + ", " + (yHero + 1) + ")");
    }

    /**
     * Метод формирует стартовую позицию для чудовищ
     */

    private void startMonsters() {
        for (int index = 0; index < numberOfMonsters; index++) {
            xMonster = (int) (Math.random() * limitX);
            yMonster = (int) (Math.random() * limitY);
            while (xMonster == 0 && yMonster == 0 || blocks.contains(board[xMonster][yMonster])) {
                xMonster = (int) (Math.random() * limitX);
                yMonster = (int) (Math.random() * limitY);
            }
            monsters.add(new ArrayList<>());
            monsters.get(index).add(xMonster);
            monsters.get(index).add(yMonster);
            board[xMonster][yMonster].locker.tryLock();
            System.out.println("Чудовище " + (index + 1) + " стартует с позиции " + "(" + (xMonster + 1) + ", " + (yMonster + 1) + ")");
        }
        System.out.println();
    }

    /**
     * Метод генерирует следующий ход бомбермена
     */

    private void getStepOfHero() {
        int random = (int) (Math.random() * 4) + 1;
        if (random == 1 && xHero + 1 < limitX && (this.move(board[xHero][yHero], board[xHero + 1][yHero]))) {
            ++xHero;
            System.out.println("Бомбермен переместился на ячейку вправо: (" + (xHero + 1) + ", " + (yHero + 1) + ")");
        } else if (random == 2 && xHero - 1 >= 0 && (this.move(board[xHero][yHero], board[xHero - 1][yHero]))) {
            --xHero;
            System.out.println("Бомбермен переместился на ячейку влево: (" + (xHero + 1) + ", " + (yHero + 1) + ")");
        } else if (random == 3 && yHero - 1 >= 0 && (this.move(board[xHero][yHero], board[xHero][yHero - 1]))) {
            --yHero;
            System.out.println("Бомбермен переместился на ячейку вверх: (" + (xHero + 1) + ", " + (yHero + 1) + ")");
        } else if (random == 4 && yHero + 1 < limitY && (this.move(board[xHero][yHero], board[xHero][yHero + 1]))) {
            ++yHero;
            System.out.println("Бомбермен переместился на ячейку вниз: (" + (xHero + 1) + ", " + (yHero + 1) + ")");
        } else {
            System.out.println("Бомбермен стоит на месте");
        }
    }

    /**
     * Метод генерирует следующий ход для чудовищ
     */

    private void getStepOfMonster() {
        for (int index = 0; index < numberOfMonsters; index++) {
            int random = (int) (Math.random() * 4) + 1;
            xMonster = monsters.get(index).get(0);
            yMonster = monsters.get(index).get(1);
            if (random == 1 && xMonster + 1 < limitX && (this.move(board[xMonster][yMonster], board[xMonster + 1][yMonster]))) {
                monsters.get(index).set(0, ++xMonster);
                System.out.println("Чудовище " + (index + 1) + " перемещается на ячейку вправо: (" + (xMonster + 1) + ", " + (yMonster + 1) + ")");
            } else if (random == 2 && xMonster - 1 >= 0 && (this.move(board[xMonster][yMonster], board[xMonster - 1][yMonster]))) {
                monsters.get(index).set(0, --xMonster);
                System.out.println("Чудовище " + (index + 1) + " перемещается на ячейку влево: (" + (xMonster + 1) + ", " + (yMonster + 1) + ")");
            } else if (random == 3 && yMonster - 1 >= 0 && (this.move(board[xMonster][yMonster], board[xMonster][yMonster - 1]))) {
                monsters.get(index).set(1, --yMonster);
                System.out.println("Чудовище " + (index + 1) + " перемещается на ячейку вверх: (" + (xMonster + 1) + ", " + (yMonster + 1) + ")");
            } else if (random == 4 && yMonster + 1 < limitY && (this.move(board[xMonster][yMonster], board[xMonster][yMonster + 1]))) {
                monsters.get(index).set(1, ++yMonster);
                System.out.println("Чудовище " + (index + 1) + " перемещается на ячейку вниз: (" + (xMonster + 1) + ", " + (yMonster + 1) + ")");
            } else {
                System.out.println("Чудовище " + (index + 1) + " стоит на месте");
            }
        }
        System.out.println();
    }

    /**
     * Метод проверяет возможность движения каждого из персонажей
     */

    private boolean move(Cell source, Cell destination) {
        boolean result = false;
        if (!destination.locker.isLocked()) {
            destination.locker.lock();
            source.locker.unlock();
            result = true;
        }
        return result;
    }

    /**
     * Класс реализует задачу потока
     */

    private class ThreadManager implements Runnable {
        public void run() {
            buildBlocks();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startHero();
            startMonsters();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getStepOfHero();
                getStepOfMonster();
            }
        }
    }

    /**
     * Запуск задачи через пул потоков
     */

    public void start() {
        pool.execute(() -> new ThreadManager().run());
    }
}
