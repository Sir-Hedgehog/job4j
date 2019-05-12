package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 07.05.2019
 */

public class ConsoleChat {
    private boolean work = true;
    public List<String> discuss(File source) throws IOException {
        String current = "";
        List<String> list = new ArrayList<>();
        List<String> result = new ArrayList<>();
        FileReader fr = new FileReader(source);
        Scanner scanner = new Scanner(fr);
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, System.getProperty("console.encoding", "utf-8")));
        while (work) {
            String str1 = br.readLine();
            String str2 = "";
            final boolean stop = str1.equals("Стоп");
            System.out.println(str1);
            if (stop) {
                result.add("Стоп");
                fr.close();
                while (!(str2 = br.readLine()).equals("Продолжить")) {
                    result.add(str2);
                }
                result.add("Продолжить");
                this.discuss(source);
            } else if (str1.equals("Закончить")) {
                result.add("Закончить");
                fr.close();
                work = false;
                break;
            } else if (!str1.equals("Стоп") && !str1.equals("Закончить")) {
                result.add(str1);
                int random = new Random().nextInt(list.size());
                current = list.get(random);
                result.add(current);
            }
        }
        return result;
    }
}

