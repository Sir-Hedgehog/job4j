package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 6.0
 * @since 16.01.2020
 */

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private int limitX;
    private int limitY;
    private double directionX = 5;
    private double directionY = 2;

    public RectangleMove(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    /**
     * Метод формирует принцип работы потока для движения мячика
     */

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            double wayX = this.rect.getX() + directionX;
            double wayY = this.rect.getY() + directionY;
            this.rect.setX(wayX);
            this.rect.setY(wayY);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (this.rect.getX() == 0 || this.rect.getX() == limitX - 10) {
                directionX = -directionX;
            }
            if (this.rect.getY() == 0 || this.rect.getY() == limitY - 10) {
                directionY = -directionY;
            }
        }
    }
}
