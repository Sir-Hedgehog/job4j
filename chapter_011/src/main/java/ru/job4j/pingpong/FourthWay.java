package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.0
 * @since 11.01.2020
 */

public class FourthWay {
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

    public void begin() {
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
            new ThirdWay(rect, limitX, limitY).begin();
        } else if (this.rect.getX() == 0) {
            new SecondWay(rect, limitX, limitY).begin();
        }
    }
}
