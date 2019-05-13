package ru.job4j.io;

import java.io.*;
import java.util.List;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 13.05.2019
 */

public class ChatTest {
    public static void main(String[] args) throws IOException {
        ConsoleChat chat = new ConsoleChat();
        File file = new File("C:\\projects\\job4j\\powerfulRandom.txt");
        List<String> list = chat.discuss(file);
        System.out.println(list);

        //System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("stdout.log")), true));

        /*final String divider = File.separator;
        String tmpDir = System.getProperty("java.io.tmpdir");
        File chatDir = new File(tmpDir + divider + "Chat");
        chatDir.mkdir();
        File test = new File(chatDir, "Chat.txt");
        file.createNewFile();
        String line;
        while ((line = br.readLine()) != null) {
            list.add(line);
            System.out.println(list);
        }
        for (String s : list) {
            System.out.println(s);
        }*/
    }
}

