package ru.job4j.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 6.0
 * @since 16.01.2020
 */

public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    /**
     * Метод запускает в новом окне игру пинг-понг
     * @param stage - окно
     */

    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        Thread thread = new Thread(new RectangleMove(rect, limitX, limitY));
        thread.start();
        stage.setOnCloseRequest(event -> thread.interrupt());
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
    }
}
