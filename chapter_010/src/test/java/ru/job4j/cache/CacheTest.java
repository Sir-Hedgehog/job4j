package ru.job4j.cache;

import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 22.12.2019
 */

public class CacheTest {
    @Test
    public void checkSurfaceCloning() {
        Object object1 = null;
        Object object2 = null;
        String path = "\\projects\\job4j\\chapter_010\\src\\main\\resources\\";
        ReaderProgram sportProgram = new ReaderProgram("Sport.txt", "Sport");
        try {
            sportProgram.read(new FileReader(path + "Sport.txt"));
            object1 = sportProgram.getKey();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ReaderProgram planetProgram = new ReaderProgram("Planets.txt", "Planets");
        try {
            planetProgram.read(new FileReader(path + "Planets.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ReaderProgram sportProgram1 = new ReaderProgram("Sport.txt", "Planets");
        try {
            sportProgram1.read(new FileReader(path + "Sport.txt"));
            object2 = sportProgram.getKey();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertThat(object1, is(object2));
    }
}
