package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 26.01.2020
 */

public class Board implements Runnable {
    private int x;
    private int y;
    private final int limitX;
    private final int limitY;
    private final ReentrantLock[][] board;
    private final ReentrantLock step = new ReentrantLock();

    public Board(int x, int y, int limitX, int limitY) {
        this.x = x;
        this.y = y;
        this.limitX = limitX;
        this.limitY = limitY;
        board = new ReentrantLock[limitX][limitY];
    }

    /**
     * Метод осуществляет проверку на возможность хода у границ поля.
     * В случае если бомбермен попытается перейти заданную границу, то он останется на той ячейке.
     * @return - результат проверки возможности хода бомбермена
     */

    private boolean checkCell() {
        boolean result;
        if (y >= 0 && y < limitY && x >= 0 && x < limitX) {
            board[x][y] = step;
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Метод описывает движение бомбермена
     */

    private void move() {
        int random = (int) (Math.random() * 4) + 1;
        if (random == 1) {
            System.out.println("Рандом 1");
            if (step.isLocked()) {
                step.unlock();
            }
            x = x + 1;
            if (checkCell() && step.tryLock()) {
                step.lock();
                System.out.println("Бомбермен перемещается на ячейку вниз: (" + x + ", " + y + ")");
            } else {
                x = x - 1;
                step.lock();
                System.out.println("Бомбермен стоит на месте");
            }
        } else if (random == 2) {
            System.out.println("Рандом 2");
            if (step.isLocked()) {
                step.unlock();
            }
            x = x - 1;
            if (checkCell() && step.tryLock()) {
                step.lock();
                System.out.println("Бомбермен перемещается на ячейку вверх: (" + x + ", " + y + ")");
            } else {
                x = x + 1;
                step.lock();
                System.out.println("Бомбермен стоит на месте");
            }
        } else if (random == 3) {
            System.out.println("Рандом 3");
            if (step.isLocked()) {
                step.unlock();
            }
            y = y + 1;
            if (checkCell() && step.tryLock()) {
                step.lock();
                System.out.println("Бомбермен перемещается на ячейку вправо: (" + x + ", " + y + ")");
            } else {
                y = y - 1;
                step.lock();
                System.out.println("Бомбермен стоит на месте");
            }
        } else if (random == 4) {
            System.out.println("Рандом 4");
            if (step.isLocked()) {
                step.unlock();
            }
            y = y - 1;
            if (checkCell() && step.tryLock()) {
                step.lock();
                System.out.println("Бомбермен перемещается на ячейку влево: (" + x + ", " + y + ")");
            } else {
                y = y + 1;
                step.lock();
                System.out.println("Бомбермен стоит на месте");
            }
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.move();
        }
    }
}
