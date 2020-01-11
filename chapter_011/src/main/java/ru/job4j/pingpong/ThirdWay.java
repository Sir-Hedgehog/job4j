package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 11.01.2020
 */

public class ThirdWay {
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

    public void begin() {
        while (true) {
            this.rect.setX(this.rect.getX() - 5);
            this.rect.setY(this.rect.getY() + 2);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.rect.getY() == limitY - 10 || this.rect.getX() == 0) {
                break;
            }
        }
        if (this.rect.getY() == limitY - 10) {
            new FourthWay(rect, limitX, limitY).begin();
        } else if (this.rect.getX() == 0) {
            new FirstWay(rect, limitX, limitY).begin();
        }
    }
}
