package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private boolean work = true;
    public List<String> discuss(File source) throws IOException {
        String str = "";
        String current = "";
        List<String> list = new ArrayList<>();
        List<String> result = new ArrayList<>();
        FileReader fr = new FileReader(source);
        Scanner scanner = new Scanner(fr);
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (work) {
            str = br.readLine();
            if (!str.equals("Стоп") && !str.equals("Продолжить") && !str.equals("Закончить")) {
                result.add(str);
                int random = new Random().nextInt(list.size());
                current = list.get(random);
                result.add(current);
            } else if (str.equals("Стоп")) {
                result.add("Стоп");
                fr.close();
            } else if (str.equals("Продолжить")) {
                result.add("Продолжить");
                this.discuss(source);
            } else if (str.equals("Закончить")) {
                result.add("Закончить");
                fr.close();
                work = false;
                break;
            }
        }
        return result;
    }
}

