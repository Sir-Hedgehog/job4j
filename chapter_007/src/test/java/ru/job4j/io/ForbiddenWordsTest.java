package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import static org.junit.Assert.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 18.03.2019
 */

public class ForbiddenWordsTest {
    @Test
    public void whenReadTheSameWordThenItDoesNotExist() {
        ForbiddenWords words = new ForbiddenWords();
        String[] works = {"homework", "classwork"};
        InputStream in = new ByteArrayInputStream("homework test classwork exam course".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        words.dropAbuses(in, out, works);
        String[] result = {"test", "exam", "course"};
        byte[] byteArray = (out).toByteArray();
        StringBuilder line = new StringBuilder();
        for (String str : result) {
            line.append(str);
        }
        assertEquals(line.toString(), new String(byteArray));
    }
}