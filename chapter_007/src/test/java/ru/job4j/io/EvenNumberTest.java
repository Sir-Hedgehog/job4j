package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 17.03.2019
 */

public class EvenNumberTest {
    @Test
    public void whenReadEvenNumberThenTrue() {
        EvenNumber number = new EvenNumber();
        boolean result1 = number.isNumber(new InputStream() {
            @Override
            public int read() {
                return 2;
            }
        });
        boolean result2 = number.isNumber(new InputStream() {
            @Override
            public int read() {
                return 3;
            }
        });
        boolean result3 = number.isNumber(new InputStream() {
            @Override
            public int read() {
                return 10;
            }
        });
        assertThat(result1, is(true));
        assertThat(result2, is(false));
        assertThat(result3, is(true));
    }

    @Test
    public void whenReadByteArrayThenTrue() {
        EvenNumber evenNumber = new EvenNumber();
        final boolean number = evenNumber.isNumber(new ByteArrayInputStream("12345678912341221256789123456780".getBytes()));
        assertTrue(number);
    }
}
