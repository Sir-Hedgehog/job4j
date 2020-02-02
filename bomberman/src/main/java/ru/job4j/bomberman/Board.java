package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 02.02.2020
 */

public class Board implements Runnable {
    private int x;
    private int y;
    private final int limitX;
    private final int limitY;
    private ReentrantLock[][] board;

    public Board(int x, int y, int limitX, int limitY) {
        this.x = x;
        this.y = y;
        this.limitX = limitX;
        this.limitY = limitY;
        board = new ReentrantLock[limitX][limitY];
    }

    /**
     * Метод генерирует следующий ход бомбермена
     * @return - получившийся ход
     */

    private ReentrantLock getStep() {
        int random = (int) (Math.random() * 4) + 1;
        if (random == 1 && x + 1 < limitX) {
            System.out.println("Рандом 1");
            x++;
            System.out.println("Бомбермен перемещается на ячейку вправо: (" + x + ", " + y + ")");
        } else if (random == 2 && x - 1 >= 0) {
            System.out.println("Рандом 2");
            x--;
            System.out.println("Бомбермен перемещается на ячейку влево: (" + x + ", " + y + ")");
        } else if (random == 3 && y - 1 >= 0) {
            System.out.println("Рандом 3");
            y--;
            System.out.println("Бомбермен перемещается на ячейку вверх: (" + x + ", " + y + ")");
        } else if (random == 4 && y + 1 < limitY) {
            System.out.println("Рандом 4");
            y++;
            System.out.println("Бомбермен перемещается на ячейку вниз: (" + x + ", " + y + ")");
        } else {
            System.out.println("Бомбермен стоит на месте");
        }
        return board[x][y];
    }

    /**
     * Метод описывает движение бомбермена
     */

    private void move(ReentrantLock source, ReentrantLock destination) {
        if (source != null && !source.isLocked()) {
            source.lock();
            if (destination.tryLock()) {
                source.unlock();
                destination.lock();
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
            this.move(board[x][y], getStep());
        }
    }
}
