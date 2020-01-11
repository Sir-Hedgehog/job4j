package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 11.01.2020
 */

public class SecondWay {
    private final Rectangle rect;
    private int limitX;
    private int limitY;

    public SecondWay(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    /**
     * Метод запускает вариант движения мячика с левого нижнего края до правого верхнего
     */

    public void begin() {
        while (true) {
            this.rect.setX(this.rect.getX() + 5);
            this.rect.setY(this.rect.getY() - 2);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.rect.getY() == 0 || this.rect.getX() == limitX - 10) {
                break;
            }
        }
        if (this.rect.getY() == 0) {
            new FirstWay(rect, limitX, limitY).begin();
        } else if (this.rect.getX() == limitX - 10) {
            new FourthWay(rect, limitX, limitY).begin();
        }
    }
}
