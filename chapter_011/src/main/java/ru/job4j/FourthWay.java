package ru.job4j;

import javafx.scene.shape.Rectangle;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 27.12.2019
 */

public class FourthWay implements Runnable {
    private final Rectangle rect;
    private int limitX;
    private int limitY;

    public FourthWay(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    /**
     * Метод запускает вариант движения мячика с правого нижнего края до левого верхнего
     */

    @Override
    public void run() {
        while (this.rect.getX() != 0  && this.rect.getY() != 0 && !Thread.currentThread().isInterrupted()) {
            this.rect.setX(this.rect.getX() - 5);
            this.rect.setY(this.rect.getY() - 2);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.rect.getY() == 0) {
            new Thread(new ThirdWay(rect, limitX, limitY)).start();
        } else if (this.rect.getX() == 0) {
            new Thread(new SecondWay(rect, limitX, limitY)).start();
        }
    }
}
