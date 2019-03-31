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
 * @since 31.03.2019
 */

public class SearchTest {
    private static final String SEPARATOR = File.separator;
    @Test
    public void whenSearchNecessaryExpansionsThenShowResult() {
        Search search = new Search();
        List<String> list = new ArrayList<>();
        list.add("lnk");
        list.add("txt");
        String way = System.getProperty("java.io.tmpdir") + SEPARATOR;
        List<File> result = search.list(way, list);
        List<File> expect = new ArrayList<>();
        File file1 = new File("C:\\Users\\18B8~1\\AppData\\Local\\Temp\\ImageDebug\\AutoPosToneMap.txt");
        File file2 = new File("C:\\Users\\18B8~1\\AppData\\Local\\Temp\\acrord32_sbx\\BroadcastMsg_1553542561.txt");
        File file3 = new File("C:\\Users\\18B8~1\\AppData\\Local\\Temp\\RedBoxLog.txt");
        File file4 = new File("C:\\Users\\18B8~1\\AppData\\Local\\Temp\\DOpera\\ready\\resources\\license.txt");
        File file5 = new File("C:\\Users\\18B8~1\\AppData\\Local\\Temp\\Браузер Opera.lnk");
        expect.add(file1);
        expect.add(file2);
        expect.add(file3);
        expect.add(file4);
        expect.add(file5);
        List<File> sortResult = search.sortName(result);
        assertThat(sortResult, is(expect));
    }
}


