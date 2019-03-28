package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 28.03.2019
 */

public class SearchTest {
    @Test
    public void whenSearchNecessaryExpansionsThenShowsResult() {
        Search search = new Search();
        List<String> list = new ArrayList<>();
        list.add("txt");
        list.add("docx");
        String way = System.getProperty("java.io.tmpdir");
        List<File> files = search.list(way, list);
        List<File> result = new ArrayList<>();
        File file1 = new File("C:\\Temp\\Moto\\Quad\\Ural.docx");
        File file2 = new File("C:\\Temp\\TV\\Sharp.txt");
        File file3 = new File("C:\\Temp\\TV\\Toshiba.docx");
        result.add(file1);
        result.add(file2);
        result.add(file3);
        for (File expect : files) {
            for (File conclusion : result) {
                assertThat(expect, is(conclusion));
            }
        }
    }
}
