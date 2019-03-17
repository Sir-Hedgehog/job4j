package ru.job4j.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 17.03.2019
 */

public class EvenNumber {
    boolean isNumber(InputStream in) {
        boolean result = false;
        try {
            int number = -1;
            int lastByte;
            do {
                lastByte = number;
                number = in.read();
            } while (number != -1);
            if (lastByte % 2 == 0) {
                result = true;
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return result;
    }
}
