package ru.job4j.io;

import java.io.IOException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 12.04.2019
 */

public class TestZip {
    public static void main(String[] args) throws IOException {
        PackZip packZip = new PackZip();
        packZip.achieve(new Args(args));
    }
}
