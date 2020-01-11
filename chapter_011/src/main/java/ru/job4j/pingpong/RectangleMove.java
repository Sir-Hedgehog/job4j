package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 4.0
 * @since 11.01.2020
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
     * Метод запускает вариант движения мячика с левого верхнего края до правого нижнего
     */

    @Override
    public void run() {
        new FirstWay(rect, limitX, limitY).begin();
    }
}
