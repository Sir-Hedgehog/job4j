package ru.job4j.download;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 30.12.2019
 */

public class FileDownload {

    /**
     * Метод запускает ограничитель скорости загрузки файла
     */

    public static void main(String[] args) {
        String url = args[0];
        String strOfNorm = args[1];
        Double norm = Double.valueOf(strOfNorm);
        Limiter limiter = new Limiter(url, norm);
        limiter.limit();
    }
}
