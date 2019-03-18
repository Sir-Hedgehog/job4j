package ru.job4j.io;

import java.io.*;
import java.util.Arrays;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 18.03.2019
 */

public class ForbiddenWords {
    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String[] words = br.readLine().split(" ");
            for (String element : abuse) {
                words = Arrays.stream(words).filter(word -> !word.equals(element)).toArray(String[]::new);
            }
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out))) {
                for (String filterWord : words) {
                    bw.write(filterWord + " ");
                }
            } catch (IOException io) {
                io.printStackTrace();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
