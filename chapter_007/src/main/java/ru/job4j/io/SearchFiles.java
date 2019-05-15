package ru.job4j.io;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SearchFiles {

    List<File> find(String parent, List<String> congruences) {
        List<File> result = new ArrayList<>();
        Queue<File> data = new LinkedList<>();
        File file = new File(parent);
        data.offer(file);
        while (!data.isEmpty()) {
            File element = data.poll();
            if (!element.isDirectory()) {
                String fileName = element.getName();
                String congruence = this.coincide(fileName);
                if (congruences.contains(congruence)) {
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

    private String coincide(String name) {
        String congruence = "";
        int index = name.length();
        if (index > 1) {
            congruence = name.substring(index);
        }
        return congruence;
    }
}
