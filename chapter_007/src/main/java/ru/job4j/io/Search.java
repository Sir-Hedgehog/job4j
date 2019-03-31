package ru.job4j.io;

import java.io.File;
import java.util.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 31.03.2019
 */

public class Search {
    List<File> list(String parent, List<String> expansions) {
        List<File> result = new ArrayList<>();
        Queue<File> data = new LinkedList<>();
        File file = new File(parent);
        data.offer(file);
        while (!data.isEmpty()) {
            File element = data.poll();
            if (!element.isDirectory()) {
                String fileName = element.getName();
                String extension = "";
                int index = fileName.lastIndexOf('.');
                if (index > 0) {
                    extension = fileName.substring(index + 1);
                }
                if (expansions.contains(extension)) {
                    result.add(element);
                }
            } else {
                File[] files = element.listFiles();
                if (files != null) {
                    for (File child : files) {
                        data.offer(child);
                    }
                }
            }
        }
        return result;
    }

    List<File> sortName(List<File> input) {
        input.sort(Comparator.comparing(File::getName));
        return input;
    }
}
