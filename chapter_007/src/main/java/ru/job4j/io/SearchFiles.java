package ru.job4j.io;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 21.05.2019
 */

public class SearchFiles {
    private final Map<String, String> commands;

    public SearchFiles(Map<String, String> commands) {
        this.commands = commands;
    }

    public List<File> findFiles() {
        List<File> result = new ArrayList<>();
        String typeOfSearch = getTypeOfSearch();
        Queue<File> queueDir = new LinkedList<>();
        queueDir.offer(new File(commands.get("-d")));
        while(!queueDir.isEmpty()) {
            File element = queueDir.poll();
            for (File file : element.listFiles()) {
                if (file.isDirectory()) {
                    queueDir.offer(file);
                } else {
                    if (checkFile(file, typeOfSearch)) {
                        result.add(file);
                    }
                }
            }
        }
        return result;
    }

    private String getTypeOfSearch() {
        String result = null;
        for(String key : commands.keySet()) {
            if (key.equals("-m")) {
                result = key;
            } else if (key.equals("-f")) {
                result = key;
            } else if (key.equals("-r")) {
                result = key;
            }
        }
        return result;
    }

    private boolean checkFile(File file, String typeOfSearch) {
        boolean result = false;
        if (typeOfSearch.equals("-m")) {
            result = checkFileByMask(file, commands.get("-m"));
        } else if (typeOfSearch.equals("-f")) {
            result = checkFileByFullName(file, commands.get("-f"));
        } else if (typeOfSearch.equals("-r")) {
            result = checkFileByRegEx(file, commands.get("-r"));
        }
        return result;
    }

    private boolean checkFileByMask(File file, String mask) {
        boolean result = false;
        String fileName = file.getName();
        if(!mask.contains("*")) {
            result = this.checkFileByFullName(file, mask);
        } else {
            String[] parts = mask.split("\\*");
            for (String part : parts) {
                if (part.isEmpty()) {
                    continue;
                }
                if (!fileName.contains(part)) {
                    result = false;
                }
            }
        }
        return result;
    }

    private boolean checkFileByFullName(File file, String fullName) {
        return file.getName().equals(fullName);
    }

    private boolean checkFileByRegEx(File file, String regEx) {
        return Pattern.matches(regEx, file.getName());
    }
}
