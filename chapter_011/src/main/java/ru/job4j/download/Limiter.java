package ru.job4j.download;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 30.12.2019
 */

public class Limiter {
    private String file; //"https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
    private double norm; //200.0;


    public Limiter(String file, double norm) {
        this.file = file;
        this.norm = norm;
    }

    /**
     * Метод ограничивает скорость загрузки файла, если произошло превышение заявленного норматива по скорости (Кбайт/сек)
     */

    public void limit() {
        double currentSpeed;
        double accurateDropping;
        long startTime;
        long endTime;
        long basicDropping;
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[256];
            int bytesRead;
            startTime = System.nanoTime();
            while ((bytesRead = in.read(dataBuffer, 0, 256)) != -1) {
                System.out.println("Загружено: " + bytesRead + " байт");
                endTime = System.nanoTime();
                double duration = (double) (endTime - startTime) / 1_000_000_000;
                System.out.println("Время загрузки текущего буфера: " + duration);
                currentSpeed = bytesRead * 0.001 / duration;
                System.out.println("Текущая скорость: " + currentSpeed + " Кбайт/сек");
                accurateDropping = currentSpeed / norm;
                basicDropping = (long) accurateDropping;
                if (currentSpeed > norm) {
                    System.out.println("Запустился процесс сброса скорости...");
                    System.out.println("Остановка потока на " + basicDropping + " миллисекунд");
                    try {
                        Thread.sleep(basicDropping);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                startTime = endTime;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
