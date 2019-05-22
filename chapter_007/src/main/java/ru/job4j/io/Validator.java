package ru.job4j.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 21.05.2019
 */

public class Validator {

    private Map<String, String> commands;

    public Validator(Map<String, String> commands) {
        this.commands = commands;
    }

    public Map<String, String> getCommands() {
        return commands;
    }

    public boolean validate() {
        boolean result = false;
        if (checkAll() && checkDirectory(commands.get("-d")) && checkRegEx(commands.get("-r"))) {
            result = true;
        }
        return result;
    }

    private boolean checkDirectory(String path) {
        boolean result = false;
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            result = true;
        }
        return result;
    }

    private boolean checkRegEx(String regEx) {
        boolean result = true;
        if (regEx != null) {
            try {
                Pattern.compile(regEx);
            } catch (PatternSyntaxException pse) {
                pse.printStackTrace();
                result = false;
            }
        }
        return result;
    }

    private List<String> getTypeOfSearch() {
        List<String> result = new ArrayList<>();
        for (String key : commands.keySet()) {
            if (key.equals("-f") || key.equals("-m") || key.equals("-r")){
                result.add(key);
            }
        }
        return result;
    }

    private boolean checkAll() {
        boolean result = true;
        if (!commands.containsKey("-d") || commands.get("-d") == null
            || !commands.containsKey("-n") || commands.get("-n") == null
            || !commands.containsKey("-o") || commands.get("-o") == null) {
            result = false;
        }
        if (this.getTypeOfSearch().size() != 1) {
            result = false;
        }
        return result;
    }
}
