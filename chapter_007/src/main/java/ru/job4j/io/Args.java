package ru.job4j.io;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 12.04.2019
 */

public class Args {
    private String directory;
    private List<String> exclude = new ArrayList<>();
    private String output;

    /**
     * @param array - массив входящих строк
     *              -d - директория, которую нужно заархивировать
     *              -o - файл, в который нужно помечтить архив
     *              -e - исключить файлы с конкретными расширениями
     */

    Args(String[] array) {
        for (int index = 0; index < array.length; index++) {
            if (array[index].equals("-d")) {
                this.directory = array[++index];
            } else if (array[index].equals("-o")) {
                this.output = array[++index];
            } else if (array[index].equals("-e")) {
                this.exclude.add(array[++index]);
            }
        }
    }

    String directory() {
        return this.directory;
    }

    List<String> exclude() {
        return this.exclude;
    }

    String output() {
        return this.output;
    }
}
