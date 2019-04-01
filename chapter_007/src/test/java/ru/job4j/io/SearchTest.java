package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 01.04.2019
 */

public class SearchTest {
    @Test
    public void whenSearchNecessaryExpansionsThenShowResult() throws IOException {
        Search search = new Search();
        List<String> list = new ArrayList<>();
        list.add("txt");
        String tmpDir = System.getProperty("java.io.tmpdir");
        File basicDir = new File(tmpDir + "Basic");
        basicDir.mkdir();
        File currentDir1 = new File(basicDir.getPath() + "\\CurrentDir1");
        currentDir1.mkdir();
        File currentDir2 = new File(basicDir.getPath() + "\\CurrentDir2");
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
        List<File> expect = new ArrayList<>();
        File expectFile1 = new File("C:\\Users\\18B8~1\\AppData\\Local\\Temp\\Basic\\CurrentDir1\\File3.txt");
        File expectFile2 = new File("C:\\Users\\18B8~1\\AppData\\Local\\Temp\\Basic\\CurrentDir2\\File4.txt");
        File expectFile3 = new File("C:\\Users\\18B8~1\\AppData\\Local\\Temp\\Basic\\File1.txt");
        expect.add(expectFile1);
        expect.add(expectFile2);
        expect.add(expectFile3);
        Collections.sort(result);
        assertThat(result, is(expect));
    }
}


