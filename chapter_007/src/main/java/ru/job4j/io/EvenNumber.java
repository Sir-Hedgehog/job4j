package ru.job4j.io;

import java.io.DataInputStream;
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
        int number;
        try (DataInputStream br = new DataInputStream(in)) {
            number = br.readInt();
            if (number % 2 == 0) {
                result = true;
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return result;
    }
}
