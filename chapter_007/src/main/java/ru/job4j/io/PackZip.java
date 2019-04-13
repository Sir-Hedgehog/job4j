package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 13.04.2019
 */

public class PackZip {
    public void achieve(Args args) throws IOException {
        String zip = args.output();
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip))) {
            File source = new File(args.directory());
            List<String> expansions = args.exclude();
            this.form(zos, source, expansions);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void form(ZipOutputStream zos, File source, List<String> expansions) throws IOException {
        byte[] buffer = new byte[4096];
        File[] files = source.listFiles();
        List<String> listOfNames = new ArrayList<>();
        List<String> listOfFiles = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    form(zos, file, expansions);
                    continue;
                }
                if (exclude(file, expansions)) {
                    listOfNames.add(file.getName());
                    listOfFiles.add(file.getPath());
                    String[] temp = {};
                    String[] filesToZip = listOfFiles.toArray(temp);
                    String[] namesToZip = listOfNames.toArray(temp);
                    for (int index = 0; index < filesToZip.length; index++) {
                        zos.putNextEntry(new ZipEntry(namesToZip[index]));
                        try (FileInputStream fis = new FileInputStream(filesToZip[index])) {
                            while (fis.read(buffer) != -1) {
                                zos.write(buffer);
                            }
                            zos.closeEntry();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private boolean exclude(File file, List<String> expansions) {
        Search search = new Search();
        boolean result = true;
        if (expansions != null) {
            while (true) {
                if (search.list(file.getPath(), expansions) != null) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
