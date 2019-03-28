package ru.job4j.io;

import java.io.File;
import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 28.03.2019
 */

public class Search {
    List<File> list(String parent, List<String> expansions) {
        List<File> result = new ArrayList<>();
        File file = new File(parent);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File current : files) {
                    if (!current.isDirectory()) {
                        String fileName = current.getName();
                        String extension = "";
                        int i = fileName.lastIndexOf('.');
                        if (i > 0) {
                            extension = fileName.substring(i + 1);
                        }
                        if (expansions.contains(extension)) {
                            result.add(file);
                        }
                    }
                }
            }
        }
        return result;
    }
}
