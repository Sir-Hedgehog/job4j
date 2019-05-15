package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SearchFilesTest {
    /*@Test
    public void whenSearchNecessaryExpansionsThenShowResult() throws IOException {
        final String divider = File.separator;
        SearchFiles search = new SearchFiles();
        List<String> list = new ArrayList<>();
        list.add("te");
        String tmpDir = System.getProperty("java.io.tmpdir");
        File basicDir = new File(tmpDir + divider + "Components");
        basicDir.mkdir();
        File hardDir = new File(basicDir.getPath() + divider + "HardDisk");
        hardDir.mkdir();
        File motherDir = new File(basicDir.getPath() + divider + "Motherboard");
        motherDir.mkdir();
        File processorDir = new File(basicDir.getPath() + divider + "Processor");
        processorDir.mkdir();
        File wd = new File(hardDir, "WesternDigital.txt");
        File seagate = new File(hardDir, "Seagate.xlsx");
        File asus = new File(motherDir, "Asus.txt");
        File msi = new File(motherDir, "MSI.txt");
        File intel = new File(processorDir, "Intel.doc");
        File amd = new File(processorDir, "AMD.doc");
        wd.createNewFile();
        seagate.createNewFile();
        asus.createNewFile();
        msi.createNewFile();
        intel.createNewFile();
        amd.createNewFile();
        List<File> result = search.find(basicDir.getPath(), list);
        List<File> expect = List.of(wd, seagate, intel);
        Collections.sort(result);
        assertTrue(result.containsAll(expect));
    }*/
}
