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
        InputStream in = ConsoleChat.class.getResourceAsStream("/powerfulRandom.txt");
        List<String> list = chat.discuss(in);
        final String divider = File.separator;
        String tmpDir = System.getProperty("java.io.tmpdir");
        File chatDir = new File(tmpDir + divider + "Chat");
        chatDir.mkdir();
        File test = new File(chatDir, "Char.txt");
        test.createNewFile();
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(test))) {
            for (String str : list) {
                writer.write(str + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

