package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 5.0
 * @since 12.01.2020
 */

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private int limitX;
    private int limitY;

    public RectangleMove(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    /**
     * Метод начинает движение потока для мячика
     */

    @Override
    public void run() {
        this.moveFirstWay(rect, limitX, limitY);
    }

    /**
     * Метод запускает вариант движения мячика с левого верхнего края до правого нижнего
     */

    private void moveFirstWay(Rectangle rect, int limitX, int limitY) {
        while (!Thread.currentThread().isInterrupted()) {
            this.rect.setX(this.rect.getX() + 5);
            this.rect.setY(this.rect.getY() + 2);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (this.rect.getX() == limitX - 10 || this.rect.getY() == limitY - 10) {
                break;
            }
        }
        if (this.rect.getX() == limitX - 10) {
            this.moveThirdWay(rect, limitX, limitY);
        } else if (this.rect.getY() == limitY - 10) {
            this.moveSecondWay(rect, limitX, limitY);
        }
    }

    /**
     * Метод запускает вариант движения мячика с левого нижнего края до правого верхнего
     */

    private void moveSecondWay(Rectangle rect, int limitX, int limitY) {
        while (!Thread.currentThread().isInterrupted()) {
            this.rect.setX(this.rect.getX() + 5);
            this.rect.setY(this.rect.getY() - 2);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (this.rect.getY() == 0 || this.rect.getX() == limitX - 10) {
                break;
            }
        }
        if (this.rect.getY() == 0) {
            this.moveFirstWay(rect, limitX, limitY);
        } else if (this.rect.getX() == limitX - 10) {
            this.moveFourthWay(rect, limitX, limitY);
        }
    }

    /**
     * Метод запускает вариант движения мячика с правого верхнего края до левого нижнего
     */

    private void moveThirdWay(Rectangle rect, int limitX, int limitY) {
        while (!Thread.currentThread().isInterrupted()) {
            this.rect.setX(this.rect.getX() - 5);
            this.rect.setY(this.rect.getY() + 2);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (this.rect.getY() == limitY - 10 || this.rect.getX() == 0) {
                break;
            }
        }
        if (this.rect.getY() == limitY - 10) {
            this.moveFourthWay(rect, limitX, limitY);
        } else if (this.rect.getX() == 0) {
            this.moveFirstWay(rect, limitX, limitY);
        }
    }

    /**
     * Метод запускает вариант движения мячика с правого нижнего края до левого верхнего
     */

    private void moveFourthWay(Rectangle rect, int limitX, int limitY) {
        while (!Thread.currentThread().isInterrupted()) {
            this.rect.setX(this.rect.getX() - 5);
            this.rect.setY(this.rect.getY() - 2);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (this.rect.getY() == 0 || this.rect.getX() == 0) {
                break;
            }
        }
        if (this.rect.getY() == 0) {
            this.moveThirdWay(rect, limitX, limitY);
        } else if (this.rect.getX() == 0) {
            this.moveSecondWay(rect, limitX, limitY);
        }
    }
}
