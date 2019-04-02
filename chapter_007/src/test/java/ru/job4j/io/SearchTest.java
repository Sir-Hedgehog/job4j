package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 02.04.2019
 */

public class SearchTest {
    @Test
    public void whenSearchNecessaryExpansionsThenShowResult() throws IOException {
        final String divider = File.separator;
        Search search = new Search();
        List<String> list = new ArrayList<>();
        list.add("txt");
        String tmpDir = System.getProperty("java.io.tmpdir");
        File basicDir = new File(tmpDir + divider + "Basic");
        basicDir.mkdir();
        File currentDir1 = new File(basicDir.getPath() + divider + "CurrentDir1");
        currentDir1.mkdir();
        File currentDir2 = new File(basicDir.getPath() + divider + "CurrentDir2");
        currentDir2.mkdir();
        File file1 = new File(basicDir, "File1.txt");
        File file2 = new File(currentDir1, "File2.xlsx");
        File file3 = new File(currentDir1, "File3.txt");
        File file4 = new File(currentDir2, "File4.txt");
        File file5 = new File(currentDir2, "File5.doc");
        file1.createNewFile();
        file2.createNewFile();
        file3.createNewFile();
        file4.createNewFile();
        file5.createNewFile();
        List<File> result = search.list(basicDir.getPath(), list);
        List<File> expect = List.of(file1, file4, file3);
        Collections.sort(result);
        assertTrue(result.containsAll(expect));
    }
}


