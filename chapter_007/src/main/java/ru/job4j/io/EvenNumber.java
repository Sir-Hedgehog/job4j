package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 16.03.2019
 */

public class EvenNumber {
    boolean isNumber(InputStream in) {
        boolean result = false;
        int number;
        try (BufferedInputStream br = new BufferedInputStream(in)) {
            number = br.read();
            if (number % 2 == 0) {
                result = true;
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return result;
    }
}
