package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import static org.junit.Assert.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 17.03.2019
 */

public class EvenNumberTest {

    @Test
    public void whenReadByteArrayThenTrue() {
        EvenNumber evenNumber = new EvenNumber();
        final boolean number = evenNumber.isNumber(new ByteArrayInputStream("12345678912341221256789123456780".getBytes()));
        assertTrue(number);
    }

    @Test
    public void whenReadByteArrayThenFalse() {
        EvenNumber evenNumber = new EvenNumber();
        final boolean number = evenNumber.isNumber(new ByteArrayInputStream("12345678912341221256789123456781".getBytes()));
        assertFalse(number);
    }

    @Test
    public void whenReadByteArrayOfCharactersThenFalse() {
        EvenNumber evenNumber = new EvenNumber();
        final boolean number = evenNumber.isNumber(new ByteArrayInputStream("мамамылараму222".getBytes()));
        assertFalse(number);
    }
}
