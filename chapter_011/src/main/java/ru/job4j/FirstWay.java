package ru.job4j;

import javafx.scene.shape.Rectangle;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 27.12.2019
 */

public class FirstWay implements Runnable {
    private final Rectangle rect;
    private int limitX;
    private int limitY;

    public FirstWay(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    /**
     * Метод запускает вариант движения мячика с левого верхнего края до правого нижнего
     */

    @Override
    public void run() {
        while (this.rect.getX() != limitX - 10 && this.rect.getY() != limitY - 10) {
            this.rect.setX(this.rect.getX() + 5);
            this.rect.setY(this.rect.getY() + 2);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.rect.getX() == limitX - 10) {
            new Thread(new ThirdWay(rect, limitX, limitY)).start();
        } else if (this.rect.getY() == limitY - 10) {
            new Thread(new SecondWay(rect, limitX, limitY)).start();
        }
    }
}
