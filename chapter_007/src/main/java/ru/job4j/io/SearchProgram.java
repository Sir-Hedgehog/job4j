package ru.job4j.io;

import com.google.common.base.Joiner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 22.05.2019
 */

public class SearchProgram {
    private final static String LN = System.lineSeparator();
    private final Validator validator;
    private final SearchFiles searchFiles;

    public SearchProgram(Validator validator, SearchFiles searchFiles) {
        this.validator = validator;
        this.searchFiles = searchFiles;
    }

    public void start() {
        if (validator.getCommands().containsKey("-help")) {
            getHelp();
        }
        if (validator.validate()) {
            writeResult(searchFiles.findFiles(), validator.getCommands().get("-o"));
            System.out.println("Результат поиска будет записан в файл: "
                    + validator.getCommands().get("-o"));
        } else {
            System.out.println("Отсутствуют необходимые аргументы. ");
            getHelp();
        }
    }

    public void getHelp() {
        System.out.println(Joiner.on(LN).join(
                "Аргументы для ввода:",
                "-d: директория, в которой будет происходить поиск в формате C:\\...\\...",
                "-n: маска, имя файла, либо регулярное выражение",
                "-m: поиск по маске, -f: поиск по имени файла, -r: поиск по регулярному выражению",
                "-o: файл, в который записывается результат поиска",
                "-help: справка по вводимым аргументам")
        );
    }

    public void writeResult(List<File> files, String logFileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFileName))) {
            bw.write("Найдены файлы: " + LN);
            for (File file : files) {
                bw.write(file.toString() + LN);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> loadArgs(String[] args) {
        Map<String, String> arguments = new HashMap<>();
        String str = null;
        for (String arg : args) {
            if (str != null) {
                arguments.put(str, arg);
                str = null;
            }
            switch (arg) {
                case "-d": str = "-d"; break;
                case "-n": str = "-n"; break;
                case "-m": arguments.put(arg, null); break;
                case "-f": arguments.put(arg, null); break;
                case "-r": arguments.put(arg, arguments.get("-n")); break;
                case "-o": str = "-o"; break;
                case "-help": arguments.put(arg, null); break;
                default: break;
            }
        }
        return arguments;
    }

    public static void main(String[] args) {
        Map<String, String> commands = SearchProgram.loadArgs(args);
        for (String argument : args) {
            System.out.println(argument);
        }
        Validator validator = new Validator(commands);
        SearchFiles searchFiles = new SearchFiles(commands);
        new SearchProgram(validator, searchFiles).start();
    }
}
