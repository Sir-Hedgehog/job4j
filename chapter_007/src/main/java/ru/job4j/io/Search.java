package ru.job4j.io;

import java.io.File;
import java.nio.file.Path;
import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 25.03.2019
 */

public class Search {
    List<File> list(String parent, List<String> expansions) {
        List<File> result = new ArrayList<>();
        File file = new File(parent);
        if (file == null) {
            result = null;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File current : files) {
                if (!current.isDirectory()) {
                    String checked = file.getName();
                    if (expansions.contains(checked)) {
                        result.add(file);
                    }
                }
            }
        }
        if (!file.isDirectory()) {
            String checked = file.getName();
            if (expansions.contains(checked)) {
                result.add(file);
            }
        }
        return result;
    }
}
