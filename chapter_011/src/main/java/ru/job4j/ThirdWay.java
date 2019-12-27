package ru.job4j;

import javafx.scene.shape.Rectangle;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 27.12.2019
 */

public class ThirdWay implements Runnable {
    private final Rectangle rect;
    private int limitX;
    private int limitY;

    public ThirdWay(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    /**
     * Метод запускает вариант движения мячика с правого верхнего края до левого нижнего
     */

    @Override
    public void run() {
        while (this.rect.getX() != 0 && this.rect.getY() != limitY - 10 && !Thread.currentThread().isInterrupted()) {
            this.rect.setX(this.rect.getX() - 5);
            this.rect.setY(this.rect.getY() + 2);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (this.rect.getY() == limitY - 10) {
            new Thread(new FourthWay(rect, limitX, limitY)).start();
        } else if (this.rect.getX() == 0) {
            new Thread(new FirstWay(rect, limitX, limitY)).start();
        }
    }
}
