package ru.job4j.cache;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 22.12.2019
 */

public class ReaderProgram extends Cache {
    public ReaderProgram(String key, Object content) {
        super(key, content);
    }

    /**
     * Метод считывает текстовый файл
     * @param fileReader - полный путь до текстового файла
     */

    public void read(FileReader fileReader) {
        try {
            int counter;
            while ((counter = fileReader.read()) != -1) {
                System.out.print((char) counter);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
