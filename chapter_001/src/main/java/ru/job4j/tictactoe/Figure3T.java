package ru.job4j.tictactoe;

import javafx.scene.shape.Rectangle;

class Figure3T extends Rectangle {
    private boolean markX = false;
    private boolean markO = false;

    Figure3T() {
    }

    Figure3T(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }

    void take(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }

    boolean hasMarkX() {
        return this.markX;
    }

    boolean hasMarkO() {
        return this.markO;
    }
}
